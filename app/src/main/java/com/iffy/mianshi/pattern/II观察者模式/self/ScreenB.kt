package com.iffy.mianshi.pattern.II观察者模式.self

class ScreenB(wd:WeatherData):Observer {
    init {
        wd.registObserver(this)
    }
    override fun updateData(data: WeatherData) {
       println("ScreenB:${data.pre}")
    }
}