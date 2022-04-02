package com.youngcha.liargameapp.application.domain

import java.time.LocalDateTime

data class Game(
    val gameCode: String,
    val roomCode: String,
    val liarUserCode: String,
    val keyword: Keyword,
    val startAt: LocalDateTime
)
