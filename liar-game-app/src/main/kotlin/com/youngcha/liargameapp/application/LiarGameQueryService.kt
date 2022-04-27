package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.Room
import com.youngcha.liargameapp.application.domain.RoomCode
import com.youngcha.liargameapp.data.LiarGameRepository
import org.springframework.stereotype.Service

@Service
class LiarGameQueryService(
    val repository: LiarGameRepository
) : RoomFinder {

    override fun findRoom(roomCode: RoomCode): Room {
        return repository.find(roomCode)!!
    }
}
