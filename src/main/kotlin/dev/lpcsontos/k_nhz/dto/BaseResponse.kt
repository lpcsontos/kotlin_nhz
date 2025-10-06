package dev.lpcsontos.k_nhz.dto

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
    val success: Boolean,
    val message: String? = null,
    val authToken: String? = null
)