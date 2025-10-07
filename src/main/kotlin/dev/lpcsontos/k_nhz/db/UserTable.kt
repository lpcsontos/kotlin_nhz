package dev.lpcsontos.k_nhz.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object UserTable : IntIdTable("users") {
    val username = varchar("username", 20)
    val password = varchar("password", 61)
    val profileslug = varchar("profileslug", 50)
    val displayname = varchar("displayname", 50)
    val description = varchar("description", 100)
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
}