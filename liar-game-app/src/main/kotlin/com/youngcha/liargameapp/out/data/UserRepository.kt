package com.youngcha.liargameapp.out.data

import com.youngcha.liargameapp.out.domain.UserEntity
import org.springframework.stereotype.Repository

@Repository
class UserRepository {

    private val repositoryByUserCode = mutableMapOf<String, UserEntity>()

    fun save(user: UserEntity): UserEntity {
        repositoryByUserCode[user.userCode] = user
        return user
    }
}