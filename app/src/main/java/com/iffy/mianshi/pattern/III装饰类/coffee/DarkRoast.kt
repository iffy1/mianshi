package com.iffy.mianshi.pattern.III装饰类.coffee

import com.iffy.mianshi.pattern.III装饰类.Beverage

class DarkRoast: Beverage() {
    override fun coast(): Double {
        return 15.0
    }

}