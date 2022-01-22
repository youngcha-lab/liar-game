package com.youngcha.liargameapp.handler

import com.youngcha.liargameapp.service.PingService
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Service
class PingHandler(
    private val pingService: PingService
) {
    fun ping(req: ServerRequest): Mono<ServerResponse> =
        pingService.ping()
            .flatMap { ServerResponse.ok().bodyValue(it) }
}