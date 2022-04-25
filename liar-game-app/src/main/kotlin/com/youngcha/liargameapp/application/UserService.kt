package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.User
import com.youngcha.liargameapp.data.RoomRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val repository: RoomRepository
) : UserCreateProcessor, UserDeleteProcessor {

    override fun createUser(roomCode: String, nickname: String): String {
        val room = repository.find(roomCode)!!
        val newUser = User(
            nickname = nickname
        )
        val newRoom = room.copy(
            users = room.users.plus(newUser),
            leader = room.leader ?: newUser
        )
        repository.save(newRoom)
        return newUser.userCode
    }

    override fun deleteUser(roomCode: String, userCode: String) {
        val room = repository.find(roomCode)!!
        val newRoom = room.copy(
            users = room.users.filter { it.userCode != userCode }
        )
        repository.save(newRoom)
    }
}
