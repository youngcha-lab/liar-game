package com.youngcha.liargameapp.out.data

import com.youngcha.liargameapp.out.domain.UserEntity
import org.springframework.stereotype.Repository

@Repository
class UserRepository {

    private val repositoryByUserCode = mutableMapOf<String, UserEntity>()
    private val repositoryByRoomCode = mutableMapOf<String, MutableList<UserEntity>>()

    fun save(user: UserEntity) {
        repositoryByUserCode[user.userCode] = user
        repositoryByRoomCode[user.roomCode]
        if (repositoryByRoomCode[user.roomCode] == null) {
            repositoryByRoomCode[user.roomCode] = mutableListOf(user)
        } else {
            repositoryByRoomCode[user.roomCode]?.add(user)
        }
    }

    fun findByRoomCode(roomCode: String): List<UserEntity> {
        return repositoryByRoomCode[roomCode] ?: emptyList()
    }
}
