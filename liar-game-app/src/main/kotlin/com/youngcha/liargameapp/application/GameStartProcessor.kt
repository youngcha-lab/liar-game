package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.Game
import com.youngcha.liargameapp.application.domain.RoomCode

interface GameStartProcessor {
    fun startGame(roomCode: RoomCode): Game
}
