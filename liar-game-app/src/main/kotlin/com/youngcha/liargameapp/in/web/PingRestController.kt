package com.youngcha.liargameapp.`in`.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PingRestController {

    @GetMapping("/api/ping")
    fun ping(): String {
        return "pong"
    }
}
