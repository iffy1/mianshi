package com.iffy.mianshi.headfirst.I策略模式.duck

class RuberDuck: Duck() {
    init {
        setFlyBehavior(FlyNoWay())
        setQuackBehavior(Squeak())
    }
}