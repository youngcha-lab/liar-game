package com.youngcha.liargameapp.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class HealthCheckRouter {

    @Bean
    fun route() = router {
        GET("/api/ping") { ServerResponse.ok().bodyValue("pong") }
    }
}