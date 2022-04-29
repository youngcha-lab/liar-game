package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.Game
import com.youngcha.liargameapp.application.domain.GameEndProcessor
import com.youngcha.liargameapp.application.domain.Room
import com.youngcha.liargameapp.application.domain.RoomCode
import com.youngcha.liargameapp.application.domain.User
import com.youngcha.liargameapp.application.domain.UserCode
import com.youngcha.liargameapp.data.LiarGameRepository
import org.springframework.stereotype.Service

@Service
class LiarGameManageService(
    val repository: LiarGameRepository
) : RoomCreateProcessor,
    UserJoinProcessor,
    UserLeaveProcessor,
    GameStartProcessor,
    GameEndProcessor {

    override fun createRoom(nickname: String): Room {
        val leader = User(nickname = nickname)
        val created = Room(leader = leader, users = listOf(leader))
        return save(created)
    }

    override fun join(roomCode: RoomCode, nickname: String): User {
        val room = find(roomCode)
        val joined = room.join(nickname)
        return save(joined)
            .userRequired(nickname = nickname)
    }

    override fun leave(roomCode: RoomCode, userCode: UserCode): User {
        val room = find(roomCode)
        val left = room.leave(userCode)
        return save(left)
            .userRequired(userCode = userCode)
    }

    override fun startGame(roomCode: RoomCode): Game {
        val room = find(roomCode)
        val started = room.startGame()
        return save(started)
            .currentGameRequired()
    }

    override fun endGame(roomCode: RoomCode): Game {
        val room = find(roomCode)
        val ended = room.endGame()
        return save(ended)
            .lastGameRequired()
    }

    private fun find(roomCode: RoomCode): Room =
        repository.find(roomCode)!!

    private fun save(room: Room): Room =
        repository.save(room)
}
