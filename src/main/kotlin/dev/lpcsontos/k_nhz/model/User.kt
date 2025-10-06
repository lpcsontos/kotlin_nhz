package dev.lpcsontos.k_nhz.model

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class User (
    val id: Int,
    val username: String,
    val description: String,
    var authToken: String? = null,
    val createdAt: String,
)