package com.youngcha.liargameapp.application.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty

class RoomSpec : FunSpec({

    test("방 생성") {
        val leader = User(nickname = "leader")

        val created = Room(
            leader = leader,
            users = listOf(leader),
        )

        created.leader shouldBe leader
        created.userRequired(created.leader.nickname)
        created.userRequired(created.leader.userCode)
        created.users.size shouldBe 1
    }

    test("방 참여") {
        val leader = User(nickname = "leader")
        val room = Room(
            leader = leader,
            users = listOf(leader),
        )

        val joined = room.join("participant")

        joined.users.size shouldBe 2
        joined.userRequired("participant").shouldNotBeNull()
    }

    test("방 나가기") {
        val leader = User(nickname = "leader")
        val participant = User(nickname = "participant")
        val room = Room(
            leader = leader,
            users = listOf(leader, participant),
        )

        val left = room.leave(participant.userCode)

        left.users.size shouldBe 1
        shouldThrow<IllegalArgumentException> {
            left.userRequired(participant.nickname)
        }
        shouldThrow<IllegalArgumentException> {
            left.userRequired(participant.userCode)
        }
    }

    test("게임 시작") {
        val leader = User(nickname = "leader")
        val participant = User(nickname = "participant")
        val room = Room(
            leader = leader,
            users = listOf(leader, participant),
        )

        val started = room.startGame()

        started.currentGameRequired().category.shouldNotBeEmpty()
        started.currentGameRequired().keyword.shouldNotBeEmpty()
        started.userRequired(nickname = started.currentGameRequired().liar.nickname)
        started.userRequired(userCode = started.currentGameRequired().liar.userCode)
        shouldThrow<IllegalArgumentException> { started.lastGameRequired() }
    }

    test("게임 종료") {
        val leader = User(nickname = "leader")
        val participant = User(nickname = "participant")
        val room = Room(
            leader = leader,
            users = listOf(leader, participant),
        )
        val started = room.startGame()

        val ended = started.endGame()

        ended.lastGameRequired().category.shouldNotBeEmpty()
        ended.lastGameRequired().keyword.shouldNotBeEmpty()
        ended.userRequired(nickname = ended.lastGameRequired().liar.nickname)
        ended.userRequired(userCode = ended.lastGameRequired().liar.userCode)
        shouldThrow<IllegalArgumentException> { ended.currentGameRequired() }
    }

    test("게임 재시작") {
        val leader = User(nickname = "leader")
        val participant = User(nickname = "participant")
        val room = Room(
            leader = leader,
            users = listOf(leader, participant),
        )
        val started = room.startGame()
        val ended = started.endGame()

        val restarted = ended.startGame()

        restarted.currentGameRequired().category.shouldNotBeEmpty()
        restarted.currentGameRequired().keyword.shouldNotBeEmpty()
        restarted.userRequired(nickname = restarted.currentGameRequired().liar.nickname)
        restarted.userRequired(userCode = restarted.currentGameRequired().liar.userCode)
        restarted.lastGameRequired() shouldBe ended.lastGameRequired()
    }
})
