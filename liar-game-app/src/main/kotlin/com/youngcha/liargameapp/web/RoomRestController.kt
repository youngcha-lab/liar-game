package com.youngcha.liargameapp.web

import com.youngcha.liargameapp.application.RoomCreateProcessor
import com.youngcha.liargameapp.application.RoomFindCommand
import com.youngcha.liargameapp.application.RoomFindProcessor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1/room"])
class RoomRestController(
    val roomCreateProcessor: RoomCreateProcessor,
    val roomFindProcessor: RoomFindProcessor
) {

    @PostMapping
    fun createRoom(): Map<String, String> {
        val roomCode = roomCreateProcessor.process()
        return mapOf("room_code" to roomCode)
    }

    @GetMapping("/{room_code}")
    fun findRoom(@PathVariable room_code: String): Map<String, Any> {
        val room = roomFindProcessor.process(
            RoomFindCommand(roomCode = room_code)
        )
        return mapOf(
            "room_code" to room.roomCode,
            "users" to room.users.map { it.nickname }
        )
    }
}
