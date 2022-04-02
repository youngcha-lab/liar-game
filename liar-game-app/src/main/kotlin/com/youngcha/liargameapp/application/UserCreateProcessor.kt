package com.youngcha.liargameapp.application

interface UserCreateProcessor {
    fun process(command: UserCreateCommand): String
}

data class UserCreateCommand(
    val roomCode: String,
    val nickname: String
)

