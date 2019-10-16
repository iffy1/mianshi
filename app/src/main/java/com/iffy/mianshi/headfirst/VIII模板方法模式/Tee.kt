package com.iffy.mianshi.headfirst.VIII模板方法模式

class Tee : CaffeineBeverage() {
    override fun brew() {
        println("泡茶叶")
    }

    override fun addCondiments() {
        println("加入柠檬")
    }

    override fun hook():Boolean{
        return false
    }
}