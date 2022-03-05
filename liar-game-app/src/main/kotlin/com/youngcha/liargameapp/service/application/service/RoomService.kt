package com.youngcha.liargameapp.service.application.service

import com.youngcha.liargameapp.service.application.processor.RoomCreateProcessor
import com.youngcha.liargameapp.out.domain.RoomEntity
import com.youngcha.liargameapp.out.data.RoomRepository
import com.youngcha.liargameapp.service.utils.UuidGenerator
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RoomService(
    val roomRepository: RoomRepository
): RoomCreateProcessor {

    override fun process(): String {
        val newRoom = RoomEntity(
            roomCode = UuidGenerator.generate(),
            createdAt = LocalDateTime.now()
        )
        roomRepository.save(newRoom)
        return newRoom.roomCode
    }
}