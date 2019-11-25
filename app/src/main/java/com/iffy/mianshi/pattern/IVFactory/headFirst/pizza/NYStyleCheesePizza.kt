package com.iffy.mianshi.pattern.IVFactory.headFirst.pizza

import com.iffy.mianshi.pattern.IVFactory.headFirst.Pizza

class NYStyleCheesePizza : Pizza(){
    init {
        println("NYStyleCheesePizza init")
        sauce="Marinara Sauce"
        name = "NY Style Sauce and cheese Pizza"
        dough = "Thin Crust Dough"
        toppings.add("Grated Reggiano Cheese")
    }
}