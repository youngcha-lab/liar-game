package com.youngcha.liargameapp.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GameController {

    @PostMapping("/api/game/start")
    fun start(): String {
        return "pong"
    }
}