package com.iffy.mianshi.pattern.VSingleton

import com.iffy.mianshi.pattern.VSingleton.hungry.BoilerHuang
import com.iffy.mianshi.pattern.VSingleton.lazy.*

fun main(){
    BoilerHuang.a()
    BoilerLaz.getInstance()?.b()
    Boiler懒汉B.instance?.b()
    懒汉demo.instance
    Boiler懒汉线程安全.getInstance()?.b()
    kotlin推荐的双重检测单例模式.mInstance.b

}


