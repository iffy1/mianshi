package com.iffy.mianshi.pattern.III装饰类.condiment

import com.iffy.mianshi.pattern.III装饰类.Beverage

class Soy(var mBeverage: Beverage) : Beverage() {
    override var mDescription: String = "我是豆浆"
    override fun getmDescription(): String {
        return mDescription
    }
    override fun coast(): Double {
        println("Soy coast")
        return mBeverage.coast() + 7.0
    }

}