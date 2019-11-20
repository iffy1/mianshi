package com.iffy.mianshi.pattern.IV工厂模式


//产品类的抽象
abstract class Pizza {
    protected var toppings = ArrayList<String>()
    protected lateinit var name: String
    protected lateinit var sauce: String
    protected lateinit var dough: String
    fun prepare() {
        println("preparing $name")
        println("Adding sauce $sauce")
        println("dough is $dough")
        toppings.forEach { println("Adding topping $it") }

    }

    open fun cut() {
        println("普通 cut the pizza")
    }

    fun bake() {
        println("普通 bake the pizza")

    }

    fun box() {
        println("普通 box the pizza")
    }

    fun getname(): String {
        return name
    }


}