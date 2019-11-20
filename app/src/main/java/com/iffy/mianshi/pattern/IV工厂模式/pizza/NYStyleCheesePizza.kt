package com.iffy.mianshi.pattern.IV工厂模式.pizza

import com.iffy.mianshi.pattern.IV工厂模式.Pizza

class NYStyleCheesePizza : Pizza(){
    init {
        println("NYStyleCheesePizza init")
        sauce="Marinara Sauce"
        name = "NY Style Sauce and cheese Pizza"
        dough = "Thin Crust Dough"
        toppings.add("Grated Reggiano Cheese")
    }
}