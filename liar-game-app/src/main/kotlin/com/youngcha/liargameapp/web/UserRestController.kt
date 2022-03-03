package com.youngcha.liargameapp.controller

import com.youngcha.liargameapp.model.CreateUserRequest
import com.youngcha.liargameapp.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserRestController(
    val userService: UserService
) {

    @PostMapping("/api/user")
    fun createUser(createUserRequest: CreateUserRequest): Map<String, String> {
        val newUser = userService.createUser(createUserRequest.nickname, createUserRequest.roomCode)
        return mapOf("user_code" to newUser.userCode)
    }
}

data class CreateUserRequest(
    val nickname: String,
    val roomCode: String
)