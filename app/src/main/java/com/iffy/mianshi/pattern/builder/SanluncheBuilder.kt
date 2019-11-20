package com.iffy.mianshi.pattern.builder

class SanluncheBuilder : Builder() {
    private var bike = Bike()

    override fun getMyBike(): Bike {
        return bike
    }

    override fun addwheal() {
        bike.add("三个轮子")
    }

    override fun addLight() {
        bike.add("三个灯")
    }

    override fun addHandlebar() {
        bike.add("一个把")
    }
}