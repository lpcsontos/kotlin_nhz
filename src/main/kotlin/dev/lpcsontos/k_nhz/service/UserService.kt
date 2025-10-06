package dev.lpcsontos.k_nhz.service

import dev.lpcsontos.k_nhz.model.User

interface UserService {
    suspend fun registerUser(params: CreateUserParams): User?
    suspend fun findUserByUsername(username: String): User?
}