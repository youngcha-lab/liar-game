package com.youngcha.liargameapp.application

import com.youngcha.liargameapp.application.domain.User

interface UserDeleteProcessor {

    fun process(userCode: String): User
}
