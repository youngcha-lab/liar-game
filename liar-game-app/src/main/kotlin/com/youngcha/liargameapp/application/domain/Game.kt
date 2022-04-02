package com.youngcha.liargameapp.application.domain

import java.time.LocalDateTime

data class Game(
    val liar: User,
    val keyword: Keyword,
    val startAt: LocalDateTime
)
