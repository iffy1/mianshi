package com.iffy.mianshi.pattern.II观察者模式.self

class ScreenC(wd:WeatherData):Observer {
    init {
        wd.registObserver(this)
    }
    override fun updateData(data: WeatherData) {
       println("ScreenC:${data.shidu}")
    }
}