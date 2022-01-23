package com.youngcha.liargameapp.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PingService(
    private val log: Logger = LoggerFactory.getLogger(PingService::class.java)
) {
    fun ping(): Mono<String> {
        log.debug("ping pong")
        return Mono.just("pong")
    }

}
