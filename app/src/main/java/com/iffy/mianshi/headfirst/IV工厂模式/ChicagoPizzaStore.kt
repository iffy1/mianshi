package com.iffy.mianshi.headfirst.IV工厂模式


import com.iffy.mianshi.headfirst.IV工厂模式.pizza.ChicagoStyleCalmPizza
import com.iffy.mianshi.headfirst.IV工厂模式.pizza.ChicagoStyleCheesePizza
import com.iffy.mianshi.headfirst.IV工厂模式.pizza.ChicagoStylePeperoniPizza
import com.iffy.mianshi.headfirst.IV工厂模式.pizza.ChicagoStyleVeggiePizza


class ChicagoPizzaStore:PizzaStore() {
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