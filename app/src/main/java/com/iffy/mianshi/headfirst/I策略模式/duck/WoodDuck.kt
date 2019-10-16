package com.iffy.mianshi.headfirst.I策略模式.duck

class WoodDuck : Duck() {
    init {
        setQuackBehavior(MuteQuack())
        setFlyBehavior(FlyNoWay())
    }
}

