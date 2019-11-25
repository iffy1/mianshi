package com.iffy.mianshi.pattern.IStrategy.duck

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