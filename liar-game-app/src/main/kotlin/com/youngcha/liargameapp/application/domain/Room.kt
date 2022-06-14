package com.youngcha.liargameapp.application.domain

import com.youngcha.liargameapp.application.UuidGenerator
import kotlin.math.roundToInt

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

    fun userRequired(nickname: String): User {
        if (this.users.none { it.nickname == nickname }) {
            throw IllegalArgumentException("no user found. nickname: $nickname")
        }
        return this.users.single { it.nickname == nickname }
    }

    fun userRequired(userCode: UserCode): User {
        if (this.users.none { it.userCode == userCode }) {
            throw IllegalArgumentException("no user found. userCode: $userCode")
        }
        return this.users.single { it.userCode == userCode }
    }

    fun leave(userCode: UserCode): Room =
        this.copy(
            users = this.users.filter { it.userCode != userCode }
        )

    fun startGame(keyword: String, category: String): Room =
        this.copy(
            currentGame = Game(
                keyword = keyword,
                category = category,
                liar = this.users.random()
            )
        )

    fun endGame(): Room =
        this.copy(
            lastGame = this.currentGame?.copy(),
            currentGame = emptyGame(),
        )

    private fun emptyGame() = null

    fun currentGameRequired(): Game =
        if (this.currentGame == null)
            throw IllegalArgumentException("not game started yet")
        else currentGame

    fun lastGameRequired(): Game =
        if (this.lastGame == null)
            throw IllegalArgumentException("no last game found")
        else lastGame
}

@JvmInline
value class RoomCode(
    val roomCode: String,
)

data class User(
    val userCode: UserCode = UserCode(UuidGenerator.generate()),
    val nickname: String,
    val profileColor: String = "#" + (Math.random() * 0xffffff).roundToInt().toString(16)
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
) {
    fun isLiar(userCode: UserCode) = this.liar.userCode == userCode
}
