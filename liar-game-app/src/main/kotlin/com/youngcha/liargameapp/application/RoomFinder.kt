package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.Room
import com.youngcha.liargameapp.application.domain.RoomCode

interface RoomFinder {
    fun findRoom(roomCode: RoomCode): Room
}
