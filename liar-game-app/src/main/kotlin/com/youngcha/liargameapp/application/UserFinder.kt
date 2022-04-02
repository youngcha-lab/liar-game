package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.User

interface UserFinder {
    fun findUser(userCode: String): User?
}
