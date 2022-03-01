package com.youngcha.liargameapp.controller

import com.youngcha.liargameapp.service.RoomService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RoomRestController(
    val roomService: RoomService
) {

    @PostMapping("/api/room")
    fun createRoom(): Map<String, String> {
        return mapOf("room_code" to roomService.createRoom().roomCode)
    }
}