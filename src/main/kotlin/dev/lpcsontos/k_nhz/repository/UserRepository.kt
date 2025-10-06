package dev.lpcsontos.k_nhz.repository

import dev.lpcsontos.k_nhz.dto.UserParams
import dev.lpcsontos.k_nhz.dto.BaseResponse

interface UserRepository {
    suspend fun registerUser(params: UserParams): BaseResponse
    suspend fun loginUser(username: String, password: String): BaseResponse
}