package dev.lpcsontos.k_nhz.service

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserParams(
    val username: String,
    val password: String,
)
