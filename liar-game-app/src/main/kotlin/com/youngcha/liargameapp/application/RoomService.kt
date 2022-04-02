package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.Room
import com.youngcha.liargameapp.data.RoomRepository
import org.springframework.stereotype.Service

@Service
class RoomService(
    val roomRepository: RoomRepository
) : RoomCreateProcessor, RoomFinderProcessor {

    override fun process(): String {
        return roomRepository.save(Room())
            .roomCode
    }

    override fun process(roomCode: String): Room {
        return roomRepository.find(roomCode)!!
    }
}
