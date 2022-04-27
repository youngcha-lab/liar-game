package com.youngcha.liargameapp.web

import com.youngcha.liargameapp.application.RoomCreateProcessor
import com.youngcha.liargameapp.application.RoomFinder
import com.youngcha.liargameapp.application.RoomJoinProcessor
import com.youngcha.liargameapp.application.RoomLeaveProcessor
import com.youngcha.liargameapp.application.domain.Game
import com.youngcha.liargameapp.application.domain.Room
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(path = ["/api/v1/room"])
class RoomRestController(
    val roomCreateProcessor: RoomCreateProcessor,
    val roomFindProcessor: RoomFinder,
    val roomJoinProcessor: RoomJoinProcessor,
    val roomLeaveProcessor: RoomLeaveProcessor,
    @Value("\${cookie.name.domain}") val cookieDomain: String,
) {

    @PostMapping
    fun createRoom(
        form: CreateUserForm,
        httpServletResponse: HttpServletResponse,
    ): Map<String, String> {
        val room = roomCreateProcessor.createRoom(form.nickname)
        httpServletResponse.addUserCodeCookie(
            userCode = room.leader.userCode,
            domain = cookieDomain
        )
        return mapOf("roomCode" to room.roomCode)
    }

    @GetMapping("/{room_code}")
    fun findRoom(
        @PathVariable("room_code") roomCode: String,
        @CookieValue("lguc") userCode: String?
    ): RoomResponse {
        val room = roomFindProcessor.findRoom(roomCode)
        return RoomResponse(
            room = RoomPresentation(
                roomCode = room.roomCode,
                users = room.users.map { it.nickname },
                currentUser = CurrentUserPresentation.of(
                    room = room,
                    userCode = userCode
                ),
                currentGame = CurrentGamePresentation.of(
                    currentGame = room.currentGame
                ),
                lastGame = LastGamePresentation.of(
                    lastGame = room.lastGame
                )
            )
        )
    }

    @PostMapping("/api/v1/room/join/{room_code}")
    fun joinRoom(
        @PathVariable("room_code") roomCode: String,
        @RequestBody form: CreateUserForm,
        httpServletResponse: HttpServletResponse,
    ): ResponseEntity<*> {
        val userCode = roomJoinProcessor.joinRoom(
            roomCode = roomCode,
            nickname = form.nickname
        )
        httpServletResponse.addUserCodeCookie(
            userCode = userCode,
            domain = cookieDomain
        )
        return ResponseEntity.noContent().build<Unit>()
    }

    @DeleteMapping("/api/v1/room/leave/{room_code}")
    fun leaveRoom(
        @PathVariable("room_code") roomCode: String,
        @CookieValue("lguc") userCode: String
    ): ResponseEntity<*> {
        roomLeaveProcessor.leaveRoom(
            roomCode = roomCode,
            userCode = userCode
        )
        return ResponseEntity.noContent().build<Unit>()
    }
}

/**
 * Request
 */
data class CreateUserForm(
    val nickname: String
)

/**
 * Response
 */
data class RoomResponse(val room: RoomPresentation)

data class RoomPresentation(
    val roomCode: String,
    val users: List<String>,
    val currentUser: CurrentUserPresentation?,
    val currentGame: CurrentGamePresentation?,
    val lastGame: LastGamePresentation?,
)

data class CurrentUserPresentation(
    val nickname: String?,
    val isLeader: Boolean,
    val isMember: Boolean,
) {
    companion object {
        fun of(room: Room, userCode: String?): CurrentUserPresentation? =
            if (userCode != null) {
                CurrentUserPresentation(
                    nickname = room.users
                        .firstOrNull { it.userCode == userCode }
                        ?.nickname,
                    isLeader = room.leader.userCode == userCode,
                    isMember = room.users.any { it.userCode == userCode }
                )
            } else null
    }
}

data class CurrentGamePresentation(
    val category: String,
    val keyword: String,
) {
    companion object {
        fun of(currentGame: Game?): CurrentGamePresentation? =
            if (currentGame != null) {
                CurrentGamePresentation(
                    category = currentGame.category,
                    keyword = currentGame.keyword,
                )
            } else null
    }
}

data class LastGamePresentation(
    val category: String,
    val keyword: String,
    val liar: String,
) {
    companion object {
        fun of(lastGame: Game?): LastGamePresentation? =
            if (lastGame != null) {
                LastGamePresentation(
                    category = lastGame.category,
                    keyword = lastGame.keyword,
                    liar = lastGame.liar.nickname,
                )
            } else null
    }
}

fun HttpServletResponse.addUserCodeCookie(userCode: String, domain: String): Unit =
    this.addCookie(
        Cookie("lguc", userCode)
            .apply {
                this.domain = domain
                this.maxAge = 1 * 24 * 60 * 60 // 1일
            }
    )
