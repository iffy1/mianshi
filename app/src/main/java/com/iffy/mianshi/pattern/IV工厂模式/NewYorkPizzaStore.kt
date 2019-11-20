package com.iffy.mianshi.pattern.IV工厂模式

import com.iffy.mianshi.pattern.IV工厂模式.pizza.NYStyleCalmPizza
import com.iffy.mianshi.pattern.IV工厂模式.pizza.NYStyleCheesePizza
import com.iffy.mianshi.pattern.IV工厂模式.pizza.NYStylePeperoniPizza
import com.iffy.mianshi.pattern.IV工厂模式.pizza.NYStyleVeggiePizza

class NewYorkPizzaStore : PizzaStore() {
    override fun createPizza(type: String): Pizza? {
        when (type) {
            PizzaType.cheese.name -> return NYStyleCheesePizza()
            PizzaType.calm.name -> return NYStyleCalmPizza()
            PizzaType.peperoni.name -> return NYStylePeperoniPizza()
            PizzaType.veggie.name -> return NYStyleVeggiePizza()
        }
        return null
    }
}