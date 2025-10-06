package dev.lpcsontos.k_nhz.config

import dev.lpcsontos.k_nhz.db.UserTable
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    val database = Database.connect(
        url = "jdbc:mysql://${Env["DB_HOST"]}:${Env["DB_PORT"]}/${Env["DB_NAME"]}",
        user = Env["DB_USER"],
        driver = "com.mysql.cj.jdbc.Driver",
        password = Env["DB_PASS"],
    )

    transaction {
        SchemaUtils.create(UserTable)
    }

}

suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
    transaction {
        block()
    }
}
