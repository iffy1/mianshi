package com.iffy.mianshi.pattern.IV工厂模式

fun main(){
   var pi = NewYorkPizzaStore().orderPizza(PizzaType.cheese.name)
    println(pi?.getname())
    pi = ChicagoPizzaStore().orderPizza(PizzaType.cheese.name)
    println(pi?.getname())
}