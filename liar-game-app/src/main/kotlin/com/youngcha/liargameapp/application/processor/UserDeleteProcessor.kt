package com.youngcha.liargameapp.application.processor

import com.youngcha.liargameapp.application.model.User

interface UserDeleteProcessor {

    fun process(userCode: String): User
}
