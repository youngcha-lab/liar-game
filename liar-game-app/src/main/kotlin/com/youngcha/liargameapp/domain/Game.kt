package com.youngcha.liargameapp.domain

import java.time.LocalDateTime

data class Game(
    val gameId: Int,
    val roomId: Int,
    val keywordId: Int,
    val liarUserId: Int,
    val startAt: LocalDateTime
)