package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.Room

interface RoomFinderProcessor {
    fun findRoom(roomCode: String): Room
}
