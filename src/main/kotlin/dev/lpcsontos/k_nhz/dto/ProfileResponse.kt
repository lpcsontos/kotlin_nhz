package dev.lpcsontos.k_nhz.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val userId: Int,
    val username: String,
)