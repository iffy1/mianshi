package com.iffy.mianshi.pattern.I策略模式.duck

class FlyNoWay: FlyBehavior {
    override fun fly() {
        println("我不会飞")
    }
}