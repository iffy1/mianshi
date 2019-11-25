package com.iffy.mianshi.pattern.IStrategy.duck

class FlyWithWings : FlyBehavior {
    override fun fly() {
       println("我用翅膀飞翔")
    }
}