package com.youngcha.liargameapp.service.application.processor

interface RoomCreateProcessor {

    /**
     * @return 생성된 방의 코드
     */
    fun process(): String
}
