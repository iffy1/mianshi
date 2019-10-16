package com.iffy.mianshi.headfirst.I策略模式.duck

fun main() {
    var wduck = WoodDuck()
    wduck.performFly()
    wduck.performQuack()

    var rduck = RuberDuck()
    rduck.performQuack()
    rduck.performFly()
    rduck.setFlyBehavior(FlyWithWings())
    rduck.performFly()
}