package com.youngcha.liargameapp.application.model

data class User(
    val userCode: String,
    val roomCode: String,
    val nickname: String,
    val isLeader: Boolean
)
