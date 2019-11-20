package com.iffy.mianshi.pattern.II观察者模式.java内置


fun main() {
    var wd = WeatherData(1, 1, 1)
    ScreenA(wd)
    ScreenB(wd)
    ScreenC(wd)
    wd.setData(2, 2, 2)
    wd.notifyObservers()

    wd.setData(6, 6, 6)
    wd.notifyObservers()
}
