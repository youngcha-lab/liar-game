package com.youngcha.liargameapp.`in`.web

import com.youngcha.liargameapp.service.application.processor.RoomCreateProcessor
import com.youngcha.liargameapp.service.application.processor.RoomFindCommand
import com.youngcha.liargameapp.service.application.processor.RoomFindProcessor
import org.springframework.web.bind.annotation.GetMapping
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

    @GetMapping
    fun findRoom(roomFindForm: RoomFindForm): Map<String, Any> {
        val room = roomFindProcessor.process(roomFindForm.buildCommand())
        return mapOf(
            "room_code" to room.roomCode,
            "users" to room.users.map { it.nickname }
        )
    }

    @JvmInline
    value class RoomFindForm(
        private val room_code: String
    ) {
        fun buildCommand() = RoomFindCommand(
            roomCode = room_code
        )
    }
}
