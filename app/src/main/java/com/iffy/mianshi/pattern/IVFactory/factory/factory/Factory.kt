package com.iffy.mianshi.pattern.IVFactory.factory.factory

import com.iffy.mianshi.pattern.IVFactory.factory.product.Product

abstract class Factory {
    abstract fun makeProduct():Product
}