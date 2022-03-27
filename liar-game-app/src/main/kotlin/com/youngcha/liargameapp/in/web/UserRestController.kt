package com.youngcha.liargameapp.`in`.web

import com.youngcha.liargameapp.application.processor.CreateUser
import com.youngcha.liargameapp.application.processor.UserCreateProcessor
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/v1/user")
class UserRestController(
    val userCreateProcessor: UserCreateProcessor,
    @Value("\${cookie.name.domain}") val cookieDomain: String
) {

    @PostMapping
    fun createUser(
        @RequestBody createUserForm: CreateUserForm,
        httpServletResponse: HttpServletResponse
    ): Map<String, String> {
        val userCode = userCreateProcessor.process(createUserForm.buildCommand())
        httpServletResponse.addCookie(
            Cookie("lguc", userCode).apply {
                domain = cookieDomain
                maxAge = 1 * 24 * 60 * 60 // 1일
            }
        )
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
