package com.youngcha.liargameapp.application.service

import com.youngcha.liargameapp.application.model.User
import com.youngcha.liargameapp.application.processor.CreateUserCommand
import com.youngcha.liargameapp.application.processor.UserCreateProcessor
import com.youngcha.liargameapp.application.processor.UserDeleteProcessor
import com.youngcha.liargameapp.application.processor.UserFinder
import com.youngcha.liargameapp.application.utils.UuidGenerator
import com.youngcha.liargameapp.out.data.RoomRepository
import com.youngcha.liargameapp.out.data.UserRepository
import com.youngcha.liargameapp.out.domain.UserEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(
    val userRepository: UserRepository,
    val roomRepository: RoomRepository
) : UserCreateProcessor, UserDeleteProcessor, UserFinder {

    override fun process(command: CreateUserCommand): String {
        val isFirstUser = roomRepository.find(command.roomCode) == null
        val newUser = UserEntity(
            roomCode = command.roomCode,
            userCode = UuidGenerator.generate(),
            nickname = command.nickname,
            isLeader = isFirstUser,
            joinedAt = LocalDateTime.now()
        )
        userRepository.save(newUser)
        return newUser.userCode
    }

    override fun findUser(userCode: String): User {
        val userEntity = userRepository.findByUserCode(userCode)!!
        return User(
            userCode = userEntity.userCode,
            roomCode = userEntity.roomCode,
            nickname = userEntity.nickname,
            isLeader = true
        )
    }

    override fun process(userCode: String): User = userRepository.delete(userCode)
        .let {
            User(
                userCode = it.userCode,
                roomCode = it.roomCode,
                nickname = it.nickname,
                isLeader = it.isLeader
            )
        }
}
