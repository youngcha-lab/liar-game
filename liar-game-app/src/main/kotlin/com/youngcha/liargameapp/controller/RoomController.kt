package com.youngcha.liargameapp.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RoomController {

    @PostMapping("/api/room")
    fun makeRoom(): String {
        return "pong"
    }
}