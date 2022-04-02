package com.youngcha.liargameapp.application.domain

import java.time.LocalDateTime

data class User(
    val userCode: String,
    val roomCode: String,
    val nickname: String,
    val isLeader: Boolean,
    val joinedAt: LocalDateTime
)
