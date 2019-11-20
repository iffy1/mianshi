package com.iffy.mianshi.pattern.builder

class Director {
    fun construct(b:Builder){
        b.addHandlebar()
        b.addwheal()
        b.addLight()
    }
}