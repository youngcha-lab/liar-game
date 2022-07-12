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
    val profileColor: String = listOf(
        "#71C7E2",
        "#23AFD6",
        "#D5B60D",
        "#E1CE60",
        "#FFB0B9",
        "#F19195",
        "#F59138",
        "#F6CFB8",
        "#B4DCE1",
        "#B8DE9A",
        "#EEC151",
        "#F4AD84",
        "#F1815C",
        "#F16A40",
        "#71B578",
        "#BFE2D6",
        "#8AD1BA",
        "#CFA0BE",
        "#AD5B8F",
        "#999FC1",
        "#C4C8DB",
        "#D4DFE3",
        "#717FB6",
        "#3D4A81",
        "#AC90BB",
        "#815799",
        "#95D4A3",
        "#96D2CF",
        "#63B8B3",
        "#F9A2BF",
        "#FADE7D",
        "#EF553C",
        "#589651",
        "#A4BF43",
        "#3E9399",
        "#DF5970",
        "#508AB2",
        "#ECDCC5",
        "#DCDFC8",
        "#B19C89",
    ).random()
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
