package com.iffy.mianshi.pattern.VIII模板方法模式

class Coffee:CaffeineBeverage() {
    override fun brew() {
        println("冲泡咖啡粉")
    }

    override fun addCondiments() {
        println("加糖喝牛奶")
    }
}