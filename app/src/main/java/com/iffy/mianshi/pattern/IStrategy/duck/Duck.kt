package com.iffy.mianshi.pattern.IStrategy.duck

open class Duck() {
    private lateinit var quackBehavior: QuackBehavior
    private lateinit var flyBehavior: FlyBehavior
    fun display() {
    }

    fun swim() {
    }

    fun performFly() {
        flyBehavior.fly()
    }

    fun performQuack() {
        quackBehavior.quack()
    }

    fun setFlyBehavior(fb: FlyBehavior) {
        flyBehavior = fb
    }

    fun setQuackBehavior(qb: QuackBehavior) {
        quackBehavior = qb
    }

}