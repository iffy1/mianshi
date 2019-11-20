package com.iffy.mianshi.pattern.IV工厂模式.pizza

import com.iffy.mianshi.pattern.IV工厂模式.Pizza

class ChicagoStyleCheesePizza : Pizza(){
    init {
        println("ChicagoStyleCheesePizza init")
        sauce="Plum Tomato Sauce"
        name = "Chicago Style Deep Dish and cheese Pizza"
        dough = "Extra Thick Crust Dough"
        toppings.add("Shredded Mozzarella Cheese")
    }
    override fun cut(){
        println("芝加哥芝士风味切法")

    }
}