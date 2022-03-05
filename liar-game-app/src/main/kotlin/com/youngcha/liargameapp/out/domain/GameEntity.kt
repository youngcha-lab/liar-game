package com.youngcha.liargameapp.out.domain

import java.time.LocalDateTime

data class GameEntity(
    val gameCode: String,
    val roomCode: String,
    val keywordCode: String,
    val liarUserCode: String,
    val startAt: LocalDateTime
)