package com.youngcha.liargameapp.web

import com.youngcha.liargameapp.application.UserCreateCommand
import com.youngcha.liargameapp.application.UserCreateProcessor
import com.youngcha.liargameapp.application.UserDeleteCommand
import com.youngcha.liargameapp.application.UserDeleteProcessor
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/v1/user")
class UserRestController(
    val userCreateProcessor: UserCreateProcessor,
    val userDeleteProcessor: UserDeleteProcessor,
    @Value("\${cookie.name.domain}") val cookieDomain: String
) {

    @PostMapping
    fun createUser(
        @RequestBody form: CreateUserForm,
        httpServletResponse: HttpServletResponse
    ): Map<String, String> {
        val command = UserCreateCommand(
            roomCode = form.room_code,
            nickname = form.nickname
        )
        val userCode = userCreateProcessor.process(command)
        httpServletResponse.addCookie(
            Cookie("lguc", userCode)
                .apply {
                    domain = cookieDomain
                    maxAge = 1 * 24 * 60 * 60 // 1일
                }
        )
        return mapOf("user_code" to userCode)
    }

    @DeleteMapping
    fun deleteUser(
        @RequestParam("room_code") roomCode: String,
        @CookieValue("lguc") userCode: String
    ): ResponseEntity<*> {
        val command = UserDeleteCommand(
            roomCode = roomCode,
            userCode = userCode
        )
        userDeleteProcessor.process(command)
        return ResponseEntity.noContent().build<Unit>()
    }
}

data class CreateUserForm(
    val room_code: String,
    val nickname: String
)
