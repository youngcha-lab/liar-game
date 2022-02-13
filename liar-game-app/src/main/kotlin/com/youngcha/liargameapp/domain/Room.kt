package com.youngcha.liargameapp.domain

import java.time.LocalDateTime

data class Room(
    val roomId: Int,
    val roomCode: String,
    val createdAt: LocalDateTime
)