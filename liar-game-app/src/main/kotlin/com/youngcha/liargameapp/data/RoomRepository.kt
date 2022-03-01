package com.youngcha.liargameapp.repository

import com.youngcha.liargameapp.domain.Room
import org.springframework.stereotype.Repository

@Repository
class RoomRepository {

    private val repositoryByRoomCode = mutableMapOf<String, Room>()

    fun save(room: Room): Room {
        repositoryByRoomCode[room.roomCode] = room
        return room
    }

    fun findByCode(roomCode: String): Room {
        return repositoryByRoomCode[roomCode]!!
    }
}