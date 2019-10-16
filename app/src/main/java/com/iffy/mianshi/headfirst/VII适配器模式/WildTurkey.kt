package com.iffy.mianshi.headfirst.VII适配器模式

class WildTurkey:Turkey {
    override fun gobble() {
        println("Gobble 叫")
    }

    override fun fly() {
            println("飞一米")
    }
}