package dev.lpcsontos.k_nhz.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginUser(
    val username: String,
    val password: String,
)
