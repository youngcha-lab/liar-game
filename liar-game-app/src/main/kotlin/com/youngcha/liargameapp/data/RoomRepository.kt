package com.youngcha.liargameapp.data

import com.youngcha.liargameapp.application.domain.Room
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class RoomRepository {

    private val repository = ConcurrentHashMap<String, Room>()

    fun save(room: Room): Room {
        repository[room.roomCode] = room
        return room
    }

    fun find(roomCode: String): Room? {
        return repository[roomCode]
    }
}
