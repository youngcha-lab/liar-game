package com.youngcha.liargameapp.domain

import java.time.LocalDateTime

data class User(
    val userId: Int,
    val roomId: Int,
    val userCode: String,
    val nickname: String,
    val joinedAt: LocalDateTime
)