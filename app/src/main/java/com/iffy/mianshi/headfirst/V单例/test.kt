package com.iffy.mianshi.headfirst.V单例

import kotlin.concurrent.thread

fun main(){
    Boiler饿汉.a()
    Boiler懒汉.getInstance()?.b()
    Boiler懒汉B.instance?.b()
    懒汉demo.instance
    Boiler懒汉线程安全.getInstance()?.b()
    kotlin推荐的双重检测单例模式.mInstance.b

}


