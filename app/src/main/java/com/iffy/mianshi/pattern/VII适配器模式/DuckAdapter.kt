package com.iffy.mianshi.pattern.VII适配器模式

class DuckAdapter(var turkey: Turkey) : Duck {
    override fun quack() {
        turkey.gobble()
    }

    override fun fly() {
        for (i in 1..5) {
            turkey.fly()
        }
    }


}