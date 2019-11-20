package com.iffy.mianshi.pattern.II观察者模式.java内置

import java.util.*
import java.util.Observer

class ScreenA(wd:WeatherData):Observer {
    override fun update(o: Observable?, arg: Any?) {
        o as WeatherData
        println("ScreenA:${o.temp}")
    }

    init {
        wd.addObserver(this)
    }

}