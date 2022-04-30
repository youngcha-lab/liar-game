package com.youngcha.liargameapp.application.domain

interface GameEndProcessor {
    fun endGame(roomCode: RoomCode): Game
}
