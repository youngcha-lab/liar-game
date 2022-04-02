package com.youngcha.liargameapp.application

interface UserDeleteProcessor {
    fun process(command: UserDeleteCommand)
}

data class UserDeleteCommand(
    val roomCode: String,
    val userCode: String
)
