package com.youngcha.liargameapp.repository

import com.youngcha.liargameapp.domain.Room
import org.springframework.stereotype.Repository

@Repository
class RoomRepository {

    private val repository = mutableMapOf<String, Room>()

    fun save(room: Room): Room {
        repository[room.roomCode] = room
        return room
    }

    fun findByRoomCode(roomCode: String): Room? {
        return repository[roomCode]
    }
}