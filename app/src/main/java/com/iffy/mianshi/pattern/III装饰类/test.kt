package com.iffy.mianshi.pattern.III装饰类

import com.iffy.mianshi.pattern.III装饰类.coffee.HouseBlend
import com.iffy.mianshi.pattern.III装饰类.condiment.Milk
import com.iffy.mianshi.pattern.III装饰类.condiment.Soy


fun main(){
    var hello_coffee:Beverage = HouseBlend()
    println("A")
    hello_coffee= Milk(hello_coffee)
    println("B")
    hello_coffee = Soy(hello_coffee)
    println("C")

    println(hello_coffee.coast())

}