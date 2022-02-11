package com.youngcha.liargameapp.controller

import com.youngcha.liargameapp.service.RoomService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RoomController(
    val roomService: RoomService
) {

    @PostMapping("/api/room")
    fun makeRoom(): Map<String, String> {
        return mapOf("room_code" to roomService.makeRoom().roomCode)
    }
}