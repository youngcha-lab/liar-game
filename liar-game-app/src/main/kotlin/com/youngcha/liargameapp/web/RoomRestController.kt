package com.youngcha.liargameapp.web

import com.youngcha.liargameapp.application.RoomCreateProcessor
import com.youngcha.liargameapp.application.RoomFinder
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1/room"])
class RoomRestController(
    val roomCreateProcessor: RoomCreateProcessor,
    val roomFindProcessor: RoomFinder
) {

    @PostMapping
    fun createRoom(): Map<String, String> {
        val roomCode = roomCreateProcessor.createRoom()
        return mapOf("roomCode" to roomCode)
    }

    @GetMapping("/{room_code}")
    fun findRoom(
        @PathVariable("room_code") roomCode: String,
        @CookieValue("lguc") userCode: String?
    ): RoomResponse {
        val room = roomFindProcessor.findRoom(roomCode)
        val response = RoomResponse(
            room = RoomPresentation(
                roomCode = room.roomCode,
                users = room.users.map { it.nickname },
                currentUser = if (userCode != null) {
                    CurrentUserPresentation(
                        nickname = room.users.firstOrNull { it.userCode == userCode }?.nickname,
                        isLeader = room.leader?.userCode == userCode,
                        isMember = room.users.any { it.userCode == userCode }
                    )
                } else null,
                game = if (room.game != null) {
                    GamePresentation(
                        category = room.game.category,
                        keyword = room.game.keyword,
                    )
                } else null
            )
        )
        return response
    }
}

data class RoomResponse(val room: RoomPresentation)

data class RoomPresentation(
    val roomCode: String,
    val users: List<String>,
    val currentUser: CurrentUserPresentation?,
    val game: GamePresentation?,
)

data class CurrentUserPresentation(
    val nickname: String?,
    val isLeader: Boolean,
    val isMember: Boolean,
)

data class GamePresentation(
    val category: String,
    val keyword: String,
)
