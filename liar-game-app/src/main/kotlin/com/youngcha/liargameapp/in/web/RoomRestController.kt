package com.youngcha.liargameapp.`in`.web

import com.youngcha.liargameapp.service.application.processor.RoomCreateProcessor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1/room"])
class RoomRestController(
    val roomCreateProcessor: RoomCreateProcessor
) {

    @PostMapping
    fun createRoom(): Map<String, String> {
        val roomCode = roomCreateProcessor.process()
        return mapOf("room_code" to roomCode)
    }
}
