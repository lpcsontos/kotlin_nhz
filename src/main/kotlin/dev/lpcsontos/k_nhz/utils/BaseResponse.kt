package dev.lpcsontos.k_nhz.utils

import dev.lpcsontos.k_nhz.model.User
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
    val success: Boolean,
    val message: String? = null,
    val data: User? = null
)

fun SuccessResponse(user: User, message: String? = null) = BaseResponse(
    success = true,
    message = message,
    data = user
)

fun ErrorResponse(message: String) = BaseResponse(
    success = false,
    message = message,
    data = null
)
