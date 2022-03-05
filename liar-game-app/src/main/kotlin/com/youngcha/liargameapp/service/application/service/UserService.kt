package com.youngcha.liargameapp.service.application.service

import com.youngcha.liargameapp.out.data.UserRepository
import com.youngcha.liargameapp.out.domain.UserEntity
import com.youngcha.liargameapp.service.application.processor.CreateUser
import com.youngcha.liargameapp.service.application.processor.UserCreateProcessor
import com.youngcha.liargameapp.service.utils.UuidGenerator
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(
    val userRepository: UserRepository
) : UserCreateProcessor {

    override fun process(command: CreateUser): String {
        val newUser = UserEntity(
            roomCode = command.roomCode,
            userCode = UuidGenerator.generate(),
            nickname = command.nickname,
            joinedAt = LocalDateTime.now()
        )
        userRepository.save(newUser)
        return newUser.userCode
    }
}