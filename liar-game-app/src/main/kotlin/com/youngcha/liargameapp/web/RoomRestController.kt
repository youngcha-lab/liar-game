package com.youngcha.liargameapp.web

import com.youngcha.liargameapp.application.RoomCreateProcessor
import com.youngcha.liargameapp.application.RoomFinderProcessor
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
    val roomFindProcessor: RoomFinderProcessor
) {

    @PostMapping
    fun createRoom(): Map<String, String> {
        val roomCode = roomCreateProcessor.createRoom()
        return mapOf("room_code" to roomCode)
    }

    @GetMapping("/{room_code}")
    fun findRoom(
        @PathVariable("room_code") roomCode: String,
        @CookieValue("lguc") userCode: String
    ): RoomResponse {
        val room = roomFindProcessor.findRoom(roomCode)
        return RoomResponse(
            room = RoomPresentation(
                roomCode = room.roomCode,
                users = room.users.map { it.nickname }.toList(),
                isLeader = room.leader?.userCode == userCode,
                game = when {
                    room.game != null -> GamePresentation(
                        category = room.game.category,
                        keyword = room.game.keyword
                    )
                    else -> null
                }
            )
        )
    }
}

data class RoomResponse(val room: RoomPresentation)

data class RoomPresentation(
    val roomCode: String,
    val users: List<String>,
    val isLeader: Boolean,
    val game: GamePresentation?
)

data class GamePresentation(
    val category: String,
    val keyword: String
)
