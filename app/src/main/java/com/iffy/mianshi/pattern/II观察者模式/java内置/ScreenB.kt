package com.iffy.mianshi.pattern.II观察者模式.java内置

import java.util.*

class ScreenB(wd:WeatherData):Observer {
    override fun update(o: Observable?, arg: Any?) {
        o as WeatherData
        println("ScreenB:${o.pre}")
    }

    init {
        wd.addObserver(this)
    }

}