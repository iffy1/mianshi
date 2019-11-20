package com.iffy.mianshi.pattern.I策略模式.duck

class RuberDuck: Duck() {
    init {
        setFlyBehavior(FlyNoWay())
        setQuackBehavior(Squeak())
    }
}