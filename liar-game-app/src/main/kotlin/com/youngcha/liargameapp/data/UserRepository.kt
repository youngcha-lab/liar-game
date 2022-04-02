package com.youngcha.liargameapp.data

import com.youngcha.liargameapp.application.domain.User
import org.springframework.stereotype.Repository

@Repository
class UserRepository {

    private val repositoryByUserCode = mutableMapOf<String, User>()
    private val repositoryByRoomCode = mutableMapOf<String, MutableList<User>>()

    fun save(user: User) {
        repositoryByUserCode[user.userCode] = user
        repositoryByRoomCode[user.roomCode]
        if (repositoryByRoomCode[user.roomCode] == null) {
            repositoryByRoomCode[user.roomCode] = mutableListOf(user)
        } else {
            repositoryByRoomCode[user.roomCode]?.add(user)
        }
    }

    fun findByRoomCode(roomCode: String): List<User> = repositoryByRoomCode[roomCode] ?: emptyList()

    fun findByUserCode(userCode: String): User? = repositoryByUserCode[userCode]

    fun delete(userCode: String): User {
        val user = repositoryByUserCode.remove(userCode)!!
        repositoryByRoomCode[user.roomCode] =
            repositoryByRoomCode[user.roomCode]!!.filter { it.userCode != userCode }.toMutableList()
        return user
    }
}
