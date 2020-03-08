package com.iffy.mianshi.basic.fanxing.basic


//用在类中的泛型
class FanXingLei<T>  {
    private val obj: T? = null
    fun sayHello(text:T){
        println(text)
    }
}