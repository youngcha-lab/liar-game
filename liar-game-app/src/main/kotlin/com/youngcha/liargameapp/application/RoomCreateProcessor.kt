package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.Room

interface RoomCreateProcessor {
    fun createRoom(leaderNickname: String): Room
}
