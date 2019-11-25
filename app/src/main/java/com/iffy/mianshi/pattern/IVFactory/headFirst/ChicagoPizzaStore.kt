package com.iffy.mianshi.pattern.IVFactory.headFirst


import com.iffy.mianshi.pattern.IVFactory.headFirst.pizza.ChicagoStyleCalmPizza
import com.iffy.mianshi.pattern.IVFactory.headFirst.pizza.ChicagoStyleCheesePizza
import com.iffy.mianshi.pattern.IVFactory.headFirst.pizza.ChicagoStylePeperoniPizza
import com.iffy.mianshi.pattern.IVFactory.headFirst.pizza.ChicagoStyleVeggiePizza


class ChicagoPizzaStore: PizzaStore() {
    override fun createPizza(type:String): Pizza? {
        when (type) {
            PizzaType.cheese.name -> return ChicagoStyleCheesePizza()
            PizzaType.calm.name -> return ChicagoStyleCalmPizza()
            PizzaType.peperoni.name -> return ChicagoStylePeperoniPizza()
            PizzaType.veggie.name -> return ChicagoStyleVeggiePizza()
        }
        return null


    }
}