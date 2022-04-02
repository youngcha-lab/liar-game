package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.User
import com.youngcha.liargameapp.data.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(
    val userRepository: UserRepository
) : UserCreateProcessor, UserDeleteProcessor, UserFinder {

    override fun process(command: CreateUserCommand): String {
        val isFirstUser = userRepository.findByRoomCode(command.roomCode).isEmpty()
        val newUser = User(
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
