package com.youngcha.liargameapp.web

import com.youngcha.liargameapp.application.RoomCreateProcessor
import com.youngcha.liargameapp.application.RoomFinderProcessor
import com.youngcha.liargameapp.application.domain.Room
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
        val roomCode = roomCreateProcessor.process()
        return mapOf("room_code" to roomCode)
    }

    @GetMapping("/{room_code}")
    fun findRoom(
        @PathVariable("room_code") roomCode: String
    ): Map<String, Room> {
        val room = roomFindProcessor.process(roomCode)
        return mapOf("room" to room)
    }
}
