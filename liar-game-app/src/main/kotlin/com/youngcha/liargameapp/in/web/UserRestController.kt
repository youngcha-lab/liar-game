package com.youngcha.liargameapp.`in`.web

import com.youngcha.liargameapp.application.processor.CreateUser
import com.youngcha.liargameapp.application.processor.UserCreateProcessor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.function.ServerResponse
import javax.servlet.http.Cookie

@RestController
@RequestMapping("/api/v1/user")
class UserRestController(
    val userCreateProcessor: UserCreateProcessor
) {

    @PostMapping
    fun createUser(createUserForm: CreateUserForm, serverResponse: ServerResponse): Map<String, String> {
        val userCode = userCreateProcessor.process(createUserForm.buildCommand())
        serverResponse.cookies()["lguc"] = Cookie("lguc", userCode).apply {
            maxAge = 1 * 24 * 60 * 60 // 1일
            secure = true
            isHttpOnly = true
        }
        return mapOf("user_code" to userCode)
    }
}

data class CreateUserForm(
    val nickname: String,
    val room_code: String
) {
    fun buildCommand() = CreateUser(
        nickname = nickname,
        roomCode = room_code
    )
}
