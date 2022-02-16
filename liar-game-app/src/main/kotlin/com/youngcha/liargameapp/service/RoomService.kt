package com.youngcha.liargameapp.service

import com.youngcha.liargameapp.domain.Room
import com.youngcha.liargameapp.utils.UuidGenerator
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RoomService {

    fun makeRoom(): Room = Room(
        roomId = 1,
        roomCode = UuidGenerator.generate(),
        createdAt = LocalDateTime.now()
    )
}