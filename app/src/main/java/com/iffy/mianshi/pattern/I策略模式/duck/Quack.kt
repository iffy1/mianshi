package com.iffy.mianshi.pattern.I策略模式.duck

class Quack: QuackBehavior {
    override fun quack() {
        println("我呱呱叫")
    }
}