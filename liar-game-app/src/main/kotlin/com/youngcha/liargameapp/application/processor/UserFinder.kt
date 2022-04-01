package com.youngcha.liargameapp.application.processor

import com.youngcha.liargameapp.application.model.User

interface UserFinder {
    fun findUser(userCode: String): User?
}
