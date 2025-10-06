package dev.lpcsontos.k_nhz.db

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object RoomTable : Table("room") {
    val room_id = integer("room_id")
    val message = varchar("message", 255)
    val created_at = datetime("created_at").clientDefault { LocalDateTime.now() }
    override val primaryKey = PrimaryKey(room_id)
}