package com.iffy.mianshi.pattern.VIIITemplate.headFirst

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