package com.iffy.mianshi.pattern.IStrategy.duck

class RuberDuck: Duck() {
    init {
        setFlyBehavior(FlyNoWay())
        setQuackBehavior(Squeak())
    }
}