package com.youngcha.liargameapp.domain

import java.time.LocalDateTime

data class User(
    val userCode: String,
    val roomCode: String,
    val nickname: String,
    val joinedAt: LocalDateTime
)