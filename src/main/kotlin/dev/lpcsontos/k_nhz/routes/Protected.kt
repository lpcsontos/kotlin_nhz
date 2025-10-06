package dev.lpcsontos.k_nhz.routes

import dev.lpcsontos.k_nhz.service.UserService
import dev.lpcsontos.k_nhz.utils.getUsername
import io.ktor.server.application.Application
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import dev.lpcsontos.k_nhz.dto.ProfileResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.plugins.ratelimit.RateLimitName
import io.ktor.server.plugins.ratelimit.rateLimit

fun Application.protectedRoutes(service: UserService) {
    routing {
        authenticate("auth-jwt") {
            rateLimit(RateLimitName("profile-lookup")) {
                get("/profile/{username}") {
                    val requestedUsername = call.parameters["username"] ?: return@get call.respond(
                        HttpStatusCode.BadRequest,
                        mapOf("error" to "Username parameter missing")
                    )

                    if (requestedUsername.length > 20 || !requestedUsername.matches(Regex("^[a-zA-Z0-9_]+$"))) {
                        return@get call.respond(
                            HttpStatusCode.BadRequest,
                            mapOf("error" to "Oops something went wrong")
                        )
                    }

                    val user = service.findUserByUsername(requestedUsername)
                    if (user != null) {
                        call.respond(ProfileResponse(userId = 0, username = user.username))
                    }
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}