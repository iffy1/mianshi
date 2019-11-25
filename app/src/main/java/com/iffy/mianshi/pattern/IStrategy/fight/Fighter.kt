package com.iffy.mianshi.pattern.IStrategy.fight

class Fighter {
    lateinit var strategy:Strategy
    fun fight(){
        strategy.fight()
    }
}