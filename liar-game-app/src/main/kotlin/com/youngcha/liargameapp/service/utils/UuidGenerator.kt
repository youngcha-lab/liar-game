package com.youngcha.liargameapp.service.utils

import java.util.*

object UuidGenerator {
    fun generate() = UUID.randomUUID().toString().replace("-", "")
}