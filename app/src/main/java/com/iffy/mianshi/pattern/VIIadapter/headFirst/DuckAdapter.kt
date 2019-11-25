package com.iffy.mianshi.pattern.VIIadapter.headFirst

class DuckAdapter(var turkey: Turkey) :
    Duck {
    override fun quack() {
        turkey.gobble()
    }

    override fun fly() {
        for (i in 1..5) {
            turkey.fly()
        }
    }


}