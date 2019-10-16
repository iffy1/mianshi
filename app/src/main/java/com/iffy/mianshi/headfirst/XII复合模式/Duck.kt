package com.iffy.mianshi.headfirst.XII复合模式

class Duck(var name:String) :QuackAble {
    override fun quack() {
        println("我是$name")
    }
}