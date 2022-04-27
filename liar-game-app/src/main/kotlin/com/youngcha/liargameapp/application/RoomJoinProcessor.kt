package com.youngcha.liargameapp.application

interface RoomJoinProcessor {
    fun joinRoom(roomCode: String, nickname: String): String
}
