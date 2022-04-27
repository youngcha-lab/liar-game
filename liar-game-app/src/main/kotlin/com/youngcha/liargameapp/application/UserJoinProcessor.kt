package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.RoomCode
import com.youngcha.liargameapp.application.domain.User

interface UserJoinProcessor {
    fun join(roomCode: RoomCode, nickname: String): User
}
