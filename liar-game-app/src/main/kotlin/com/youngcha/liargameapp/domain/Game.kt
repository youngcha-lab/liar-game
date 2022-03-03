package com.youngcha.liargameapp.domain

import java.time.LocalDateTime

data class Game(
    val gameCode: String,
    val roomCode: String,
    val keywordCode: String,
    val liarUserCode: String,
    val startAt: LocalDateTime
)