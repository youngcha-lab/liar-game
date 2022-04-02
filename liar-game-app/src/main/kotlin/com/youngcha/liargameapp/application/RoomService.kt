package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.Room
import com.youngcha.liargameapp.application.domain.User
import com.youngcha.liargameapp.data.RoomRepository
import com.youngcha.liargameapp.data.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RoomService(
    val roomRepository: RoomRepository,
    val userRepository: UserRepository
) : RoomCreateProcessor, RoomFindProcessor {

    override fun process(): String {
        val newRoom = Room(
            roomCode = UuidGenerator.generate(), createdAt = LocalDateTime.now()
        )
        roomRepository.save(newRoom)
        return newRoom.roomCode
    }

    override fun process(findRoomCommand: RoomFindCommand): Room {
        val room = roomRepository.find(findRoomCommand.roomCode)
            ?: throw IllegalArgumentException("no room found. roomCode: " + findRoomCommand.roomCode)
        val users = userRepository.findByRoomCode(findRoomCommand.roomCode)
        return Room(
            roomCode = room.roomCode,
            users = users.map {
                User(
                    userCode = it.userCode,
                    roomCode = room.roomCode,
                    nickname = it.nickname,
                    isLeader = it.isLeader
                )
            }
        )
    }
}
