package com.iffy.mianshi.pattern.III装饰类.condiment

import com.iffy.mianshi.pattern.III装饰类.Beverage

class Mocha(var mBeverage: Beverage) : Beverage() {
    override var mDescription: String = "我是摩卡"
    override fun getmDescription(): String {
        return mDescription
    }
    override fun coast(): Double {
        println("Mocha coast")
        return mBeverage.coast() + 6.0
    }
}