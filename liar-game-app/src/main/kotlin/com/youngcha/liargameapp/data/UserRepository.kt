package com.youngcha.liargameapp.repository

import com.youngcha.liargameapp.domain.User
import org.springframework.stereotype.Repository

@Repository
class UserRepository {

    private val repositoryByUserCode = mutableMapOf<String, User>()

    fun save(user: User): User {
        repositoryByUserCode[user.userCode] = user
        return user
    }
}