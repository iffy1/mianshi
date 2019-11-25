package com.iffy.mianshi.pattern.IVFactory.factory.factory

import com.iffy.mianshi.pattern.IVFactory.factory.product.Bike
import com.iffy.mianshi.pattern.IVFactory.factory.product.Product

class BikeFactory:Factory() {
        override fun makeProduct(): Bike {
            return Bike()
        }
}