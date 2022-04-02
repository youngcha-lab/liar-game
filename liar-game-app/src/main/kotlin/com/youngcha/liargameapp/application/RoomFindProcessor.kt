package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.Room

interface RoomFindProcessor {

    /**
     * @param findRoomCommand 방 조회 명령
     * @return 방의 정보
     */
    fun process(findRoomCommand: RoomFindCommand): Room
}

@JvmInline
value class RoomFindCommand(val roomCode: String)
