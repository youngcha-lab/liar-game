package com.youngcha.liargameapp.service

import com.youngcha.liargameapp.domain.User
import com.youngcha.liargameapp.repository.UserRepository
import com.youngcha.liargameapp.utils.UuidGenerator
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(
    val userRepository: UserRepository
) {

    fun createUser(nickname: String, roomCode: String): User {
        val newUser = User(
            roomCode = roomCode,
            userCode = UuidGenerator.generate(),
            nickname = nickname,
            joinedAt = LocalDateTime.now()
        )
        userRepository.save(newUser)
        return newUser
    }
}