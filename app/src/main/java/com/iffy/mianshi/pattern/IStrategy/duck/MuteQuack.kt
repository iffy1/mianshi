package com.iffy.mianshi.pattern.IStrategy.duck

class MuteQuack : QuackBehavior {
    override fun quack() {
      println("我不会叫")
    }

}