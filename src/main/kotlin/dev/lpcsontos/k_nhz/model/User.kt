package dev.lpcsontos.k_nhz.model

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class User (
    val id: Int,
    val username: String,
    val description: String,
    val profileslug: String,
    val displayname: String,
    var authToken: String? = null,
    val createdAt: String,
)