package com.youngcha.liargameapp.application.domain

import com.youngcha.liargameapp.application.UuidGenerator
import java.time.LocalDateTime

data class Room(
    val roomCode: String = UuidGenerator.generate(),
    val users: List<User> = emptyList(),
    val leader: User? = null,
    val game: Game? = null,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
