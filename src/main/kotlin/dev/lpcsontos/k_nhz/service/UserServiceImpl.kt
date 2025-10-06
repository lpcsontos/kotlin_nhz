package dev.lpcsontos.k_nhz.service

import org.jetbrains.exposed.sql.insertAndGetId
import dev.lpcsontos.k_nhz.config.Env
import dev.lpcsontos.k_nhz.model.User
import dev.lpcsontos.k_nhz.config.dbQuery
import dev.lpcsontos.k_nhz.db.UserTable
import dev.lpcsontos.k_nhz.security.hash
import dev.lpcsontos.k_nhz.security.verify
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import java.time.LocalDateTime

class UserServiceImpl : UserService {
    override suspend fun registerUser(params: CreateUserParams): User? {
        return dbQuery {
            val insertedId = UserTable.insertAndGetId {
                it[username] = params.username
                it[password] = hash(params.password, "${Env["HASH_ROUNDS"]}".toInt())
                it[createdAt] = LocalDateTime.now()
            }

            UserTable.selectAll().where{UserTable.id eq insertedId.value}
                .map { rowToUser(it) }
                .singleOrNull()
        }
    }

    override suspend fun authUser(username: String, password: String): User? {
        return dbQuery {
            val row = UserTable.selectAll().where { UserTable.username eq username }
                .singleOrNull()

            if (row != null) {
                val storedHash = row[UserTable.password]
                if (verify(password, storedHash)) {
                    rowToUser(row)
                } else null
            } else null
        }
    }

    override suspend fun findUserByUsername(name: String): User? {
        val user = dbQuery {
            UserTable.selectAll().where{UserTable.username eq name}
                .map { rowToUser(it) }
                .singleOrNull()
        }
        return user
    }

    private fun rowToUser(row: ResultRow?): User? {
        return if(row == null) null
        else User(
            id = row[UserTable.id].value,
            username = row[UserTable.username],
            createdAt = row[UserTable.createdAt].toString()
        )
    }
}