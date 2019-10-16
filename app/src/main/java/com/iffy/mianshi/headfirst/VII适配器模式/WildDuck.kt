package com.iffy.mianshi.headfirst.VII适配器模式

class WildDuck:Duck {
    override fun quack() {
        println("嘎嘎叫")
    }

    override fun fly() {
        println("飞五米")
    }
}