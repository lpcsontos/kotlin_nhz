package dev.lpcsontos.k_nhz.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserParams(
    val username: String,
    val password: String,
    val description: String,
)