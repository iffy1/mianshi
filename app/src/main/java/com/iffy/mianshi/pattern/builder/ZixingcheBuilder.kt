package com.iffy.mianshi.pattern.builder

class ZixingcheBuilder : Builder() {
    private var bike = Bike()

    override fun getMyBike(): Bike {
        return bike
    }

    override fun addwheal() {
        bike.add("两个轮子")
    }

    override fun addLight() {
        bike.add("两个灯")
    }

    override fun addHandlebar() {
        bike.add("一个把")
    }
}