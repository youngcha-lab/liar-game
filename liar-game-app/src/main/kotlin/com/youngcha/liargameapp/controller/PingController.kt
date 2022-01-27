package com.youngcha.liargameapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PingController {

    @GetMapping("/api/ping")
    fun ping(): String {
        return "pong"
    }
}