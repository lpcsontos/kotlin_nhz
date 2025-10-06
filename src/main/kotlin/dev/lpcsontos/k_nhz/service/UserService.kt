package dev.lpcsontos.k_nhz.service

import dev.lpcsontos.k_nhz.dto.UserParams
import dev.lpcsontos.k_nhz.model.User

interface UserService {
    suspend fun registerUser(params: UserParams): User?
    suspend fun findUserByUsername(username: String): User?
    suspend fun authUser(username: String, password: String): User?
}