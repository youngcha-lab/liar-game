package com.youngcha.liargameapp.web

import com.youngcha.liargameapp.application.GameStartProcessor
import com.youngcha.liargameapp.application.RoomCreateProcessor
import com.youngcha.liargameapp.application.RoomFinder
import com.youngcha.liargameapp.application.UserJoinProcessor
import com.youngcha.liargameapp.application.UserLeaveProcessor
import com.youngcha.liargameapp.application.domain.Game
import com.youngcha.liargameapp.application.domain.GameEndProcessor
import com.youngcha.liargameapp.application.domain.Room
import com.youngcha.liargameapp.application.domain.RoomCode
import com.youngcha.liargameapp.application.domain.UserCode
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
    val userJoinProcessor: UserJoinProcessor,
    val userLeaveProcessor: UserLeaveProcessor,
    val gameStartProcessor: GameStartProcessor,
    val gameEndProcessor: GameEndProcessor,
    @Value("\${cookie.name.domain}") val cookieDomain: String,
) {

    @PostMapping
    fun createRoom(
        @RequestBody form: CreateUserForm,
        httpServletResponse: HttpServletResponse,
    ): RoomCodeResponse {
        val room = roomCreateProcessor.createRoom(form.nickname)
        httpServletResponse.addUserCodeCookie(
            userCode = room.leader.userCode,
            domain = cookieDomain
        )
        return RoomCodeResponse(roomCode = room.roomCode)
    }

    @GetMapping("/{room_code}")
    fun findRoom(
        @PathVariable("room_code") roomCode: String,
        @CookieValue(USER_CODE_COOKIE_NAME) userCode: String?
    ): RoomResponse {
        val room = roomFindProcessor.findRoom(RoomCode(roomCode))
        return RoomResponse(
            room = RoomPresentation(
                roomCode = room.roomCode,
                users = room.users.map { it.nickname },
                currentUser = CurrentUserPresentation.of(
                    room = room,
                    userCode = UserCode.ofNullable(userCode)
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

    @PostMapping("/{room_code}/user/join")
    fun join(
        @PathVariable("room_code") roomCode: String,
        @RequestBody form: CreateUserForm,
        httpServletResponse: HttpServletResponse,
    ): ResponseEntity<*> {
        val user = userJoinProcessor.join(
            roomCode = RoomCode(roomCode),
            nickname = form.nickname
        )
        httpServletResponse.addUserCodeCookie(
            userCode = user.userCode,
            domain = cookieDomain
        )
        return ResponseEntity.noContent().build<Unit>()
    }

    @DeleteMapping("/{room_code}/user/leave")
    fun leave(
        @PathVariable("room_code") roomCode: String,
        @CookieValue(USER_CODE_COOKIE_NAME) userCode: String
    ): ResponseEntity<*> {
        userLeaveProcessor.leave(
            roomCode = RoomCode(roomCode),
            userCode = UserCode(userCode)
        )
        return ResponseEntity.noContent().build<Unit>()
    }

    @PostMapping("/{room_code}/game/start")
    fun startGame(
        @PathVariable("room_code") roomCode: String,
    ): CurrentGamePresentation {
        val game = gameStartProcessor.startGame(
            roomCode = RoomCode(roomCode)
        )
        return CurrentGamePresentation(
            keyword = game.keyword,
            category = game.category
        )
    }

    @DeleteMapping("/{room_code}/game/end")
    fun endGame(
        @PathVariable("room_code") roomCode: String,
    ): LastGamePresentation {
        val game = gameEndProcessor.endGame(
            roomCode = RoomCode(roomCode)
        )
        return LastGamePresentation(
            keyword = game.keyword,
            category = game.category,
            liar = game.liar.nickname,
        )
    }

    companion object {
        const val USER_CODE_COOKIE_NAME = "lguc"
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
data class RoomCodeResponse(val roomCode: RoomCode)

data class RoomResponse(val room: RoomPresentation)

data class RoomPresentation(
    val roomCode: RoomCode,
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
        fun of(room: Room, userCode: UserCode?): CurrentUserPresentation? =
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

fun HttpServletResponse.addUserCodeCookie(userCode: UserCode, domain: String): Unit =
    this.addCookie(
        Cookie("lguc", userCode.userCode)
            .apply {
                this.domain = domain
                this.maxAge = 1 * 24 * 60 * 60 // 1일
            }
    )