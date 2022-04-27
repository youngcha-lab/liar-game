package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.RoomCode
import com.youngcha.liargameapp.application.domain.User
import com.youngcha.liargameapp.application.domain.UserCode

interface UserLeaveProcessor {
    fun leave(roomCode: RoomCode, userCode: UserCode): User
}
