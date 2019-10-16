package com.iffy.mianshi.headfirst.VII适配器模式


fun main() {
    var duck = DuckAdapter(WildTurkey())
    duck.quack()
    duck.fly()
}
