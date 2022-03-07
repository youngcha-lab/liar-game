package com.youngcha.liargameapp.application.processor

import com.youngcha.liargameapp.application.model.Room

interface RoomFindProcessor {

    /**
     * @param findRoomCommand 방 조회 명령
     * @return 방의 정보
     */
    fun process(findRoomCommand: RoomFindCommand): Room
}

@JvmInline
value class RoomFindCommand(val roomCode: String)
