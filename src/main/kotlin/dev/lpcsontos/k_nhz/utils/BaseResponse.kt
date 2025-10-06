package dev.lpcsontos.k_nhz.utils

import dev.lpcsontos.k_nhz.model.User
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
    val success: Boolean,
    val message: String? = null,
    val authToken: String? = null
)

fun SuccessResponse(token: String, message: String? = null) = BaseResponse(
    success = true,
    message = message,
    authToken = token
)

fun ErrorResponse(message: String) = BaseResponse(
    success = false,
    message = message,
    authToken = null
)
