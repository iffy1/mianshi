package com.iffy.mianshi.headfirst.II观察者模式.java内置

import java.util.*

class ScreenC(wd:WeatherData):Observer {
    override fun update(o: Observable?, arg: Any?) {
        o as WeatherData
        println("ScreenC:${o.shidu}")

    }
    init {
        wd.addObserver(this)
    }

}