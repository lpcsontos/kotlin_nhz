package dev.lpcsontos.k_nhz.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.configureSecurity() {
    val jwtAudience = "jwt-audience"
    val jwtDomain = "https://jwt-provider-domain/"
    val jwtRealm = "kotlin nagyhazi chat app"
    val jwtSecret = "secret"
    authentication {
        jwt("auth-jwt"){
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtDomain)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }
            challenge { _, _ ->
                call.respond(
                    io.ktor.http.HttpStatusCode.Unauthorized,
                    mapOf("error" to "Token is not valid or has expired")
                )
            }
        }
    }
}