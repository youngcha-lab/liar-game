package com.youngcha.liargameapp.application.domain

import com.youngcha.liargameapp.application.UuidGenerator

data class Room(
    val roomCode: String = UuidGenerator.generate(),
    val users: List<User> = emptyList(),
    val leader: User,
    val currentGame: Game? = null,
    val lastGame: Game? = null,
) {

    fun join(nickname: String): Room {
        if (isInGame()) {
            throw IllegalArgumentException("already game started")
        }
        if (alreadyJoined(nickname)) {
            throw IllegalArgumentException("already joined user. nickname: $nickname")
        }
        return this.copy(
            users = this.users.plus(
                User(nickname = nickname)
            )
        )
    }

    private fun alreadyJoined(nickname: String): Boolean =
        this.users.any { it.nickname == nickname }

    private fun isInGame(): Boolean = currentGame != null

    fun getUserCode(nickname: String): String =
        this.users.single { it.nickname == nickname }.userCode

    fun leave(userCode: String): Room =
        this.copy(
            users = this.users.filter { it.userCode != userCode }
        )
}

data class User(
    val userCode: String = UuidGenerator.generate(),
    val nickname: String
)

data class Game(
    val keyword: String,
    val category: String,
    val liar: User,
)
