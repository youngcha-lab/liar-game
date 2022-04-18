package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.Room
import com.youngcha.liargameapp.data.RoomRepository
import org.springframework.stereotype.Service

@Service
class RoomService(
    val roomRepository: RoomRepository
) : RoomCreateProcessor, RoomFinder {

    override fun createRoom(): String {
        return roomRepository.save(Room())
            .roomCode
    }

    override fun findRoom(roomCode: String): Room {
        return roomRepository.find(roomCode)!!
    }
}
