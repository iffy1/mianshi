package com.iffy.mianshi.pattern.IStrategy.duck

class FlyNoWay: FlyBehavior {
    override fun fly() {
        println("我不会飞")
    }
}