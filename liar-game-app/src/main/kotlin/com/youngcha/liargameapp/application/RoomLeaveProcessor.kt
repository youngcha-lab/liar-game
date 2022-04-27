package com.youngcha.liargameapp.application

interface RoomLeaveProcessor {
    fun leaveRoom(roomCode: String, userCode: String)
}
