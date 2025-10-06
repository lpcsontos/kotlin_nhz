package dev.lpcsontos.k_nhz.repository

import dev.lpcsontos.k_nhz.db.UserTable.username
import dev.lpcsontos.k_nhz.service.CreateUserParams
import dev.lpcsontos.k_nhz.service.JWTService
import dev.lpcsontos.k_nhz.utils.BaseResponse
import dev.lpcsontos.k_nhz.service.UserService
import dev.lpcsontos.k_nhz.utils.ErrorResponse
import dev.lpcsontos.k_nhz.utils.SuccessResponse

class UserRepositoryImpl(
    private val userService: UserService,
    private val jwtService: JWTService
) : UserRepository {
    override suspend fun registerUser(params: CreateUserParams): BaseResponse {
        return if(isUsernameExist(params.username)) ErrorResponse(message = "Username already taken")
        else{
            val user = userService.registerUser(params)
            if(user != null) {
                val token = jwtService.generateToken(user.id, user.username)
                user.authToken = token
                SuccessResponse(user = user, message = "Success")
            }
            else ErrorResponse(message = "User could not be created error")
        }
    }

    override suspend fun loginUser(
        username: String,
        password: String
    ): BaseResponse {
        val user = userService.findUserByUsername(username)
        return if (user != null) {
            val token = jwtService.generateToken(user.id, user.username)
            user.authToken = token
            SuccessResponse(user = user, message = "Login successful")
        } else {
            ErrorResponse(message = "Invalid username or password")
        }
    }

    private suspend fun isUsernameExist(username: String): Boolean{
        return userService.findUserByUsername(username) != null
    }
}