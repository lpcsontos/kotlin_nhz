package dev.lpcsontos.k_nhz.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import dev.lpcsontos.k_nhz.config.Env
import java.util.*

class JWTService {
    private val jwtAudience = "jwt-audience"
    private val jwtDomain = "https://jwt-provider-domain/"
    private val jwtSecret = "${Env["JWT_SECRET"]}"
    private val algorithm = Algorithm.HMAC256(jwtSecret)

    fun generateToken(userId: Int, username: String): String {
        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(jwtDomain)
            .withClaim("userId", userId)
            .withClaim("username", username)
            .withExpiresAt(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24 hours
            .sign(algorithm)
    }
}