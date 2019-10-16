package com.iffy.mianshi.headfirst.I策略模式.duck

class FlyWithWings : FlyBehavior {
    override fun fly() {
       println("我用翅膀飞翔")
    }
}