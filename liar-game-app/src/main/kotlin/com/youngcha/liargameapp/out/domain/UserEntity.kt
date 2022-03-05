package com.youngcha.liargameapp.out.domain

import java.time.LocalDateTime

data class UserEntity(
    val userCode: String,
    val roomCode: String,
    val nickname: String,
    val joinedAt: LocalDateTime
)