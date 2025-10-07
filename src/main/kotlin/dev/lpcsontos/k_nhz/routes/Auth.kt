package dev.lpcsontos.k_nhz.routes

import dev.lpcsontos.k_nhz.dto.LoginUser
import dev.lpcsontos.k_nhz.repository.UserRepository
import dev.lpcsontos.k_nhz.dto.UserParams
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.plugins.ratelimit.RateLimitName
import io.ktor.server.plugins.ratelimit.rateLimit
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.authRoutes(repository: UserRepository) {
    routing{
        rateLimit(RateLimitName("auth")) {
            route("/auth") {
                post("/register") {
                    val parameters = call.receive<UserParams>()
                    val result = repository.registerUser(parameters)
                    val statusCode = if (result.success) {
                        HttpStatusCode.Created
                    } else {
                        HttpStatusCode.BadRequest
                    }
                    call.respond(statusCode, result)
                }

                post("/login") {
                    val parameters = call.receive<LoginUser>()
                    val result = repository.loginUser(parameters.username, parameters.password)
                    val statusCode = if (result.success) {
                        HttpStatusCode.OK
                    } else {
                        HttpStatusCode.Unauthorized
                    }
                    call.respond(statusCode, result)
                }
            }
        }
    }
}