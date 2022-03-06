package com.youngcha.liargameapp.application.service

import com.youngcha.liargameapp.out.data.RoomRepository
import com.youngcha.liargameapp.out.data.UserRepository
import com.youngcha.liargameapp.out.domain.RoomEntity
import com.youngcha.liargameapp.application.model.Room
import com.youngcha.liargameapp.application.model.User
import com.youngcha.liargameapp.application.processor.RoomCreateProcessor
import com.youngcha.liargameapp.application.processor.RoomFindCommand
import com.youngcha.liargameapp.application.processor.RoomFindProcessor
import com.youngcha.liargameapp.application.utils.UuidGenerator
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RoomService(
    val roomRepository: RoomRepository, val userRepository: UserRepository
) : RoomCreateProcessor, RoomFindProcessor {

    override fun process(): String {
        val newRoom = RoomEntity(
            roomCode = UuidGenerator.generate(), createdAt = LocalDateTime.now()
        )
        roomRepository.save(newRoom)
        return newRoom.roomCode
    }

    override fun process(findRoomCommand: RoomFindCommand): Room {
        val room = roomRepository.find(findRoomCommand.roomCode)
            ?: throw IllegalArgumentException("no room found. roomCode: " + findRoomCommand.roomCode)
        val users = userRepository.findByRoomCode(findRoomCommand.roomCode)
        return Room(roomCode = room.roomCode, users = users.map { User.of(it) })
    }
}