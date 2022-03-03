package com.youngcha.liargameapp.utils

import java.util.*

object UuidGenerator {
    fun generate() = UUID.randomUUID().toString().replace("-", "")
}