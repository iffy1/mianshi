package com.iffy.mianshi.pattern.VIIITemplate.headFirst

abstract class CaffeineBeverage {

    fun prepare() {
        boilWater()
        brew()
        pourInCup()
        addCondiments()
        if (hook()) {
            println("钩子事件")
        }
    }

    fun boilWater() {
        println("煮水")
    }

    abstract fun brew()

    fun pourInCup() {
        println("倒入茶杯")
    }

    abstract fun addCondiments()

    open fun hook(): Boolean {
        return true
    }
}