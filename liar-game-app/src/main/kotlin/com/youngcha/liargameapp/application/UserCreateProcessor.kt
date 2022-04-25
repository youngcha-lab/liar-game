package com.youngcha.liargameapp.application

interface UserCreateProcessor {
    fun createUser(roomCode: String, nickname: String): String
}
