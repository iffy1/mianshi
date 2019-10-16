package com.iffy.mianshi.headfirst.I策略模式.duck

class Squeak: QuackBehavior {
    override fun quack() {
        println("我吱吱叫")
    }
}