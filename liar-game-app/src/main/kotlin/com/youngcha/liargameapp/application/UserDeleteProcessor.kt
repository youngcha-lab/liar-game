package com.youngcha.liargameapp.application

interface UserDeleteProcessor {
    fun deleteUser(roomCode: String, userCode: String)
}
