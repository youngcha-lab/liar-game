package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.User
import com.youngcha.liargameapp.data.RoomRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val repository: RoomRepository
) : UserCreateProcessor, UserDeleteProcessor {

    override fun process(command: UserCreateCommand): String {
        val room = repository.find(command.roomCode)!!
        val newUser = User(
            nickname = command.nickname
        )
        val newRoom = room.users.plus(newUser)
            .let {
                room.copy(
                    users = it,
                    leader = room.leader ?: newUser
                )
            }
        repository.save(newRoom)
        return newUser.userCode
    }

    override fun process(command: UserDeleteCommand) {
        val room = repository.find(command.roomCode)!!
        val newRoom = room.users.filter { it.userCode != command.userCode }
            .let { room.copy(users = it) }
        repository.save(newRoom)
    }
}
