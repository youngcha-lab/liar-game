package com.youngcha.liargameapp.application.model

import com.youngcha.liargameapp.out.domain.UserEntity

data class User(
    val userCode: String,
    val nickname: String
) {
    companion object {
        fun of(userEntity: UserEntity) = User(
            userCode = userEntity.userCode,
            nickname = userEntity.nickname
        )
    }
}
