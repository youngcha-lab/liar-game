package com.youngcha.liargameapp.service

import com.youngcha.liargameapp.domain.User
import com.youngcha.liargameapp.repository.RoomRepository
import com.youngcha.liargameapp.repository.UserRepository
import com.youngcha.liargameapp.utils.UuidGenerator
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(
    val userRepository: UserRepository,
    val roomRepository: RoomRepository
) {

    fun createUser(nickname: String, roomCode: String): User {
        val room = roomRepository.findByCode(roomCode)
        val newUser = User(
            userId = 0,
            roomId = room.roomId,
            userCode = UuidGenerator.generate(),
            nickname = nickname,
            joinedAt = LocalDateTime.now()
        )
        userRepository.save(newUser)
        return newUser
    }
}