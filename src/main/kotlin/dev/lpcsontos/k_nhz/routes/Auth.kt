package dev.lpcsontos.k_nhz.routes

import dev.lpcsontos.k_nhz.repository.UserRepository
import dev.lpcsontos.k_nhz.service.CreateUserParams
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.authRoutes(repository: UserRepository) {
    routing{
        route("/auth") {
            post("/register"){
                val parameters = call.receive<CreateUserParams>()
                val result = repository.registerUser(parameters)
                val statusCode = if (result.success) {
                    HttpStatusCode.Created
                } else {
                    HttpStatusCode.BadRequest
                }
                call.respond(statusCode, result)
            }

            post("/login"){
                val parameters = call.receive<CreateUserParams>()
                val result = repository.loginUser(parameters. username, parameters.password)
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