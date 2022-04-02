package com.youngcha.liargameapp.application.domain

import com.youngcha.liargameapp.application.UuidGenerator

data class User(
    val userCode: String = UuidGenerator.generate(),
    val nickname: String
)
