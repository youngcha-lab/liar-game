package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.Room
import com.youngcha.liargameapp.application.domain.RoomCode

interface GameStartProcessor {
    fun startGame(roomCode: RoomCode): Room
}
