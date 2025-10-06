package dev.lpcsontos.k_nhz

import dev.lpcsontos.k_nhz.config.configureDatabases
import dev.lpcsontos.k_nhz.config.configureMonitoring
import dev.lpcsontos.k_nhz.config.configureRouting
import dev.lpcsontos.k_nhz.config.configureSecurity
import dev.lpcsontos.k_nhz.config.configureSerialization
import dev.lpcsontos.k_nhz.config.configureSockets
import dev.lpcsontos.k_nhz.repository.UserRepository
import dev.lpcsontos.k_nhz.service.UserServiceImpl
import dev.lpcsontos.k_nhz.repository.UserRepositoryImpl
import dev.lpcsontos.k_nhz.routes.authRoutes
import dev.lpcsontos.k_nhz.service.JWTService
import dev.lpcsontos.k_nhz.service.UserService
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureMonitoring()
    configureSerialization()
    configureDatabases()
    configureSockets()
    configureRouting()
    configureSecurity()

    val userService: UserService = UserServiceImpl()
    val jwtService = JWTService()
    val repository: UserRepository = UserRepositoryImpl(userService, jwtService)

    authRoutes(repository)
}
