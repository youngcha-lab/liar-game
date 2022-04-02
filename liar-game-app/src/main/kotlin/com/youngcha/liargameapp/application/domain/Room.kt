package com.youngcha.liargameapp.application.domain

import java.time.LocalDateTime

data class Room(
    val roomCode: String,
    val createdAt: LocalDateTime,
    val users: List<User>,
    val game: Game?
)
