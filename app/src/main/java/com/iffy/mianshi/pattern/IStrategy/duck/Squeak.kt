package com.iffy.mianshi.pattern.IStrategy.duck

class Squeak: QuackBehavior {
    override fun quack() {
        println("我吱吱叫")
    }
}