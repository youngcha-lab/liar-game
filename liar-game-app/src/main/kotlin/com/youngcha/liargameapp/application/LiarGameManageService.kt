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
        return save(Room(leader = leader, users = listOf(leader)))
    }

    override fun join(roomCode: RoomCode, nickname: String): User {
        val room = find(roomCode)
        return save(room.join(nickname))
            .getUser(nickname = nickname)
    }

    override fun leave(roomCode: RoomCode, userCode: UserCode): User {
        val room = find(roomCode)
        return save(room.leave(userCode))
            .getUser(userCode = userCode)
    }

    override fun startGame(roomCode: RoomCode): Game {
        val room = find(roomCode)
        return save(room.startGame())
            .getCurrentGameRequired()
    }

    override fun endGame(roomCode: RoomCode): Game {
        val room = find(roomCode)
        return save(room.endGame())
            .getLastGameRequired()
    }

    private fun find(roomCode: RoomCode): Room =
        repository.find(roomCode)!!

    private fun save(room: Room): Room =
        repository.save(room)
}
