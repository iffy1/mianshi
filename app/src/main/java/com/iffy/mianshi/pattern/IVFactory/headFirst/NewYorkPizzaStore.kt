package com.iffy.mianshi.pattern.IVFactory.headFirst

import com.iffy.mianshi.pattern.IVFactory.headFirst.pizza.NYStyleCalmPizza
import com.iffy.mianshi.pattern.IVFactory.headFirst.pizza.NYStyleCheesePizza
import com.iffy.mianshi.pattern.IVFactory.headFirst.pizza.NYStylePeperoniPizza
import com.iffy.mianshi.pattern.IVFactory.headFirst.pizza.NYStyleVeggiePizza

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