package com.iffy.mianshi.pattern.IStrategy.duck

class Quack: QuackBehavior {
    override fun quack() {
        println("我呱呱叫")
    }
}