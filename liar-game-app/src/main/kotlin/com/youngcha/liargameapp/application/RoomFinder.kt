package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.Room

interface RoomFinder {
    fun findRoom(roomCode: String): Room
}
