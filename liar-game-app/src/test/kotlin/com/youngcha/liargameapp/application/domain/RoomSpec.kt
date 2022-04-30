package com.youngcha.liargameapp.application.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty

class RoomSpec : FunSpec({

    test("방을 생성할 수 있다.") {
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

    test("방에 참여할 수 있다.") {
        val leader = User(nickname = "leader")
        val room = Room(
            leader = leader,
            users = listOf(leader),
        )

        val joined = room.join("participant")

        joined.users.size shouldBe 2
        joined.userRequired("participant").shouldNotBeNull()
    }

    test("이미 참여한 방에는 또 참여할 수 없다.") {
        // TODO: not implemented yet
    }

    test("이미 있는 닉네임으로 방에 참여할 수 없다.") {
        // TODO: not implemented yet
    }

    test("방장은 방에서 나갈 수 있다.") {
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

    test("참여자는 방에서 나갈 수 있다.") {
        // TODO: not implemented yet 
    }

    test("방장이 방에서 나가면 방과 게임이 종료된다.") {
        // TODO: not implemented yet 
    }

    test("참여자가 방에서 나가도 방과 게임이 유지된다.") {
        // TODO: not implemented yet 
    }

    test("라이어 참여자가 방에서 나가면 라이어가 바뀐다.") {
        // TODO: not implemented yet 
    }

    test("방장은 게임을 시작할 수 있다.") {
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

    test("참여자는 게임을 시작할 수 없다.") {
        // TODO: not implemented yet 
    }

    test("라이어는 라이어 전용 키워드를 본다.") {
        // TODO: not implemented yet
    }

    test("방장은 게임을 종료할 수 있다.") {
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

    test("참여자는 게임을 종료할 수 없다.") {
        // TODO: not implemented yet 
    }

    test("방장은 게임을 재시작 할 수 있다.") {
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
