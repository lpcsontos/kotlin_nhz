package dev.lpcsontos.k_nhz.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val description: String,
    val username: String,
)