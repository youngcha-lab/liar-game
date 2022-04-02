package com.youngcha.liargameapp.web

import com.youngcha.liargameapp.application.CreateUserCommand
import com.youngcha.liargameapp.application.UserCreateProcessor
import com.youngcha.liargameapp.application.UserDeleteProcessor
import com.youngcha.liargameapp.application.UserFinder
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
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
    val userDeleteProcessor: UserDeleteProcessor,
    val userFinder: UserFinder,
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

    @GetMapping
    fun getUser(@CookieValue("lguc") userCode: String): Map<String, Any> {
        val user = userFinder.findUser(userCode)!!
        return mapOf(
            "room_code" to user.roomCode, "nickname" to user.nickname, "is_leader" to user.isLeader
        )
    }

    @DeleteMapping
    fun deleteUser(@CookieValue("lguc") userCode: String): Map<String, Any> {
        val deletedUser = userDeleteProcessor.process(userCode)
        return mapOf(
            "room_code" to deletedUser.roomCode, "nickname" to deletedUser.nickname, "is_leader" to deletedUser.isLeader
        )
    }
}

data class CreateUserForm(
    val nickname: String,
    val room_code: String
) {
    fun buildCommand() = CreateUserCommand(
        nickname = nickname, roomCode = room_code
    )
}
