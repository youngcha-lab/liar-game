package com.youngcha.liargameapp.data

import com.youngcha.liargameapp.application.domain.Room
import com.youngcha.liargameapp.application.domain.RoomCode
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class LiarGameRepository {

    private val repository = ConcurrentHashMap<RoomCode, Room>()

    fun save(room: Room): Room {
        repository[room.roomCode] = room
        return room
    }

    fun find(roomCode: RoomCode): Room? {
        return repository[roomCode]
    }
}
