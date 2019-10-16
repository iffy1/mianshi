package com.iffy.mianshi.headfirst.III装饰类.condiment

import com.iffy.mianshi.headfirst.III装饰类.Beverage

class Whip(var mBeverage: Beverage) : Beverage() {
    override var mDescription: String = "我是奶油"
    override fun getmDescription(): String {
        return mDescription
    }
    override fun coast(): Double {
        println("Whip coast")
        return mBeverage.coast() + 8.0
    }

}