package com.youngcha.liargameapp.web

import com.youngcha.liargameapp.application.UserCreateProcessor
import com.youngcha.liargameapp.application.UserDeleteProcessor
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/v1/room/{room_code}/user")
class UserRestController(
    val userCreateProcessor: UserCreateProcessor,
    val userDeleteProcessor: UserDeleteProcessor,
    @Value("\${cookie.name.domain}") val cookieDomain: String
) {

    @PostMapping
    fun createUser(
        @PathVariable("room_code") roomCode: String,
        @RequestBody form: CreateUserForm,
        httpServletResponse: HttpServletResponse
    ): ResponseEntity<*> {
        val userCode = userCreateProcessor.createUser(
            roomCode = roomCode,
            nickname = form.nickname
        )
        httpServletResponse.addCookie(
            Cookie("lguc", userCode)
                .apply {
                    domain = cookieDomain
                    maxAge = 1 * 24 * 60 * 60 // 1일
                }
        )
        return ResponseEntity.noContent().build<Unit>()
    }

    @DeleteMapping
    fun deleteUser(
        @PathVariable("room_code") roomCode: String,
        @CookieValue("lguc") userCode: String
    ): ResponseEntity<*> {
        userDeleteProcessor.deleteUser(
            roomCode = roomCode,
            userCode = userCode
        )
        return ResponseEntity.noContent().build<Unit>()
    }
}

data class CreateUserForm(
    val nickname: String
)