package dev.lpcsontos.k_nhz.utils

import dev.lpcsontos.k_nhz.dto.BaseResponse
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import java.security.SecureRandom

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

fun random50(): String {
    val ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
    val RNG = SecureRandom()
    val sb = StringBuilder(50)
    repeat(50) { sb.append(ALPHABET[RNG.nextInt(ALPHABET.length)]) }
    return sb.toString()
}