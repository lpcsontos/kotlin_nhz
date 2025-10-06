package dev.lpcsontos.k_nhz.utils

import dev.lpcsontos.k_nhz.dto.BaseResponse
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun ApplicationCall.getUsername(): String {
    val principal = principal<JWTPrincipal>()
    return principal!!.payload.getClaim("username").asString()
}

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