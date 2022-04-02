package com.youngcha.liargameapp.application

interface RoomCreateProcessor {

    /**
     * @return 생성된 방의 코드
     */
    fun process(): String
}
