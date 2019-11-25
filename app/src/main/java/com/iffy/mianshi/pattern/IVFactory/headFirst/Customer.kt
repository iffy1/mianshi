package com.iffy.mianshi.pattern.IVFactory.headFirst

fun main(){
   var pi = NewYorkPizzaStore()
       .orderPizza(PizzaType.cheese.name)
    println(pi?.getname())
    pi = ChicagoPizzaStore()
        .orderPizza(PizzaType.cheese.name)
    println(pi?.getname())
}