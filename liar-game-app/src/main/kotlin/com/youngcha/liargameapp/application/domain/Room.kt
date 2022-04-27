package com.youngcha.liargameapp.application.domain

import com.youngcha.liargameapp.application.UuidGenerator

data class Room(
    val roomCode: RoomCode = RoomCode(UuidGenerator.generate()),
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

    fun getUser(nickname: String): User =
        this.users.single { it.nickname == nickname }

    fun getUser(userCode: UserCode): User =
        this.users.single { it.userCode == userCode }

    fun leave(userCode: UserCode): Room =
        this.copy(
            users = this.users.filter { it.userCode != userCode }
        )

    fun startGame(): Room =
        this.copy(
            currentGame = Game(
                keyword = "Food",
                category = "Banana",
                liar = this.users.random()
            )
        )

    fun endGame(): Room =
        this.copy(
            lastGame = this.currentGame,
            currentGame = emptyGame(),
        )

    private fun emptyGame() = null

    fun getCurrentGameRequired(): Game =
        if (this.currentGame == null)
            throw IllegalArgumentException("empty current game")
        else currentGame

    fun getLastGameRequired(): Game =
        if (this.lastGame == null)
            throw IllegalArgumentException("empty last game")
        else lastGame
}

@JvmInline
value class RoomCode(
    val roomCode: String,
)

data class User(
    val userCode: UserCode = UserCode(UuidGenerator.generate()),
    val nickname: String
)

@JvmInline
value class UserCode(
    val userCode: String,
) {
    companion object {
        fun ofNullable(userCode: String?): UserCode? =
            userCode?.let { UserCode(userCode) }
    }
}

data class Game(
    val keyword: String,
    val category: String,
    val liar: User,
)
