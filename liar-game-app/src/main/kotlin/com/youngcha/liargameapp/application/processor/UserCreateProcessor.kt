package com.youngcha.liargameapp.application.processor

interface UserCreateProcessor {

    /**
     * @param command 유저 생성 명령
     * @return 생성된 유저의 코드
     */
    fun process(command: CreateUserCommand): String
}

data class CreateUserCommand(
    val nickname: String,
    val roomCode: String
)
