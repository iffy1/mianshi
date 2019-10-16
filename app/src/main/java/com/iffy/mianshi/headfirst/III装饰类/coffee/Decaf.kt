package com.iffy.mianshi.headfirst.III装饰类.coffee

import com.iffy.mianshi.headfirst.III装饰类.Beverage

class Decaf: Beverage() {
    override fun coast(): Double {
        return 20.0
    }
}