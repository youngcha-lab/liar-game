package com.youngcha.liargameapp.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class PingController {

    @GetMapping(path = ["/api/ping"])
    @ResponseBody
    fun ping(): String {
        return "pong"
    }
}