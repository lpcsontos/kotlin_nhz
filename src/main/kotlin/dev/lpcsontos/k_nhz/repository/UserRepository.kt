package dev.lpcsontos.k_nhz.repository

import dev.lpcsontos.k_nhz.service.CreateUserParams
import dev.lpcsontos.k_nhz.utils.BaseResponse

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse
    suspend fun loginUser(username: String, password: String): BaseResponse
}