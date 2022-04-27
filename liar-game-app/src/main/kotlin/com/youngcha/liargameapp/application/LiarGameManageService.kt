package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.Room
import com.youngcha.liargameapp.application.domain.User
import com.youngcha.liargameapp.data.LiarGameRepository
import org.springframework.stereotype.Service

@Service
class LiarGameManageService(
    val repository: LiarGameRepository
) : RoomCreateProcessor, RoomJoinProcessor, RoomLeaveProcessor {

    override fun createRoom(leaderNickname: String): Room {
        val leader = User(nickname = leaderNickname)
        return repository.save(
            Room(
                leader = leader,
                users = listOf(leader)
            )
        )
    }

    override fun joinRoom(roomCode: String, nickname: String): String {
        val room = repository.find(roomCode)!!
        return repository.save(room.join(nickname))
            .getUserCode(nickname)
    }

    override fun leaveRoom(roomCode: String, userCode: String) {
        val room = repository.find(roomCode)!!
        repository.save(room.leave(userCode))
    }
}
