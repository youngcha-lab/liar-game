package com.youngcha.liargameapp.application.utils

import java.util.*

object UuidGenerator {
    fun generate() = UUID.randomUUID().toString().replace("-", "")
}
