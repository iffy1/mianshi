package com.iffy.mianshi.basic.fanxing.basic

fun main() {
    FanXing.set("我是泛型方法")
    FanXingLei<String>().sayHello("你好泛型类")

    val fanXinTest = FanXingLeiB(10)
    println(fanXinTest.value)
    fanXinTest.value = 9
    println(fanXinTest.value)
    println(fanXinTest.value)

    //通配符
    var list = ArrayList<String>()
    var listB = ArrayList<Int>()
    test(list)
    test(listB)

}

fun test(list: ArrayList<*>) {

}

