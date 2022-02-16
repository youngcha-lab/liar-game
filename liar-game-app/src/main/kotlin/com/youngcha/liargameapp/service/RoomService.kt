package com.youngcha.liargameapp.service

import com.youngcha.liargameapp.domain.Room
import com.youngcha.liargameapp.repository.RoomRepository
import com.youngcha.liargameapp.utils.UuidGenerator
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RoomService(
    val roomRepository: RoomRepository
) {

    fun makeRoom(): Room {
        val newRoom = Room(
            roomId = 0,
            roomCode = UuidGenerator.generate(),
            createdAt = LocalDateTime.now()
        )
        roomRepository.save(newRoom)
        return newRoom
    }
}