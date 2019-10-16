package com.iffy.mianshi.headfirst.III装饰类

//饮料类
abstract class Beverage {
   open var mDescription: String = "我是饮料"
    open fun getmDescription(): String {
        return mDescription
    }

    abstract fun coast():Double
}