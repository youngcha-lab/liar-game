package com.youngcha.liargameapp.data

import com.youngcha.liargameapp.application.domain.Room
import org.springframework.stereotype.Repository

@Repository
class RoomRepository {

    private val repositoryByRoomCode = mutableMapOf<String, Room>()

    fun save(room: Room): Room {
        repositoryByRoomCode[room.roomCode] = room
        return room
    }

    fun find(roomCode: String): Room? {
        return repositoryByRoomCode[roomCode]
    }
}
