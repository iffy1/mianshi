package com.iffy.mianshi.pattern.IVFactory.factory.factory

import com.iffy.mianshi.pattern.IVFactory.factory.product.Car
import com.iffy.mianshi.pattern.IVFactory.factory.product.Product

class CarFactory:Factory() {
    override fun makeProduct(): Product {
        return Car()
    }


}