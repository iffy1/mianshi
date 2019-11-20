package com.iffy.mianshi.pattern.builder

abstract class Builder {
    abstract fun addwheal()

    abstract fun addLight()

    abstract fun addHandlebar()

    abstract fun getMyBike():Bike
}