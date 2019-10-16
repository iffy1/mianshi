package com.iffy.mianshi.headfirst.IV工厂模式

import com.iffy.mianshi.headfirst.IV工厂模式.Pizza

//创造者的抽象
abstract class PizzaStore {
    fun orderPizza(type: String): Pizza?{
        var pizza = createPizza(type)
        pizza?.prepare()
        pizza?.bake()
        pizza?.cut()
        pizza?.box()
        return pizza
    }

    abstract fun createPizza(type: String): Pizza?
}