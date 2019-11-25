package com.iffy.mianshi.pattern.VIIITemplate.headFirst

class Coffee: CaffeineBeverage() {
    override fun brew() {
        println("冲泡咖啡粉")
    }

    override fun addCondiments() {
        println("加糖喝牛奶")
    }
}