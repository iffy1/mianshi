package com.iffy.mianshi.pattern.III装饰类.condiment

import com.iffy.mianshi.pattern.III装饰类.Beverage

class Milk(var mBeverage: Beverage) : Beverage() {
    override var mDescription: String = "我是牛奶"
    override fun getmDescription(): String {
        return mDescription
    }

    override fun coast(): Double {
        println("Milk coast")
        return 5.0 + mBeverage.coast()
    }
}