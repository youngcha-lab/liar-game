package com.youngcha.liargameapp.out.data

import com.youngcha.liargameapp.out.domain.RoomEntity
import org.springframework.stereotype.Repository

@Repository
class RoomRepository {

    private val repositoryByRoomCode = mutableMapOf<String, RoomEntity>()

    fun save(room: RoomEntity): RoomEntity {
        repositoryByRoomCode[room.roomCode] = room
        return room
    }

    fun find(roomCode: String): RoomEntity? {
        return repositoryByRoomCode[roomCode]
    }

}
