package com.youngcha.liargameapp.out.domain

import java.time.LocalDateTime

data class RoomEntity(
    val roomCode: String,
    val createdAt: LocalDateTime
)