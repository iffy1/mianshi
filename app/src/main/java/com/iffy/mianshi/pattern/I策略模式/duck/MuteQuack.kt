package com.iffy.mianshi.pattern.I策略模式.duck

class MuteQuack : QuackBehavior {
    override fun quack() {
      println("我不会叫")
    }

}