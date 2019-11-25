package com.iffy.mianshi.pattern.IStrategy.duck

class WoodDuck : Duck() {
    init {
        setQuackBehavior(MuteQuack())
        setFlyBehavior(FlyNoWay())
    }
}

