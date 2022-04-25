package com.youngcha.liargameapp.application

import java.util.UUID

object UuidGenerator {
    fun generate() = UUID.randomUUID().toString().replace("-", "").substring(0, 10)
}
