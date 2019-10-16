package com.iffy.mianshi.headfirst.VIII模板方法模式

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