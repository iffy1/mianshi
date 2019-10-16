package com.iffy.mianshi.headfirst.III装饰类.coffee

import com.iffy.mianshi.headfirst.III装饰类.Beverage

//黑咖啡
class HouseBlend: Beverage(){
    override fun coast():Double {
     return 10.0
    }

}