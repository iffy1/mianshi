package com.iffy.mianshi.headfirst.II观察者模式.self

fun main(){

    var wd=WeatherData(1,1,1)
    ScreenA(wd)
    ScreenB(wd)
    ScreenC(wd)

    wd.setData(1,1,1)
    wd.needNotify(true)
    wd.setData(2,2,2)
    wd.setData(3,3,3)
    wd.needNotify(false)
    wd.setData(4,4,4)
    wd.needNotify(true)
    wd.setData(5,5,5)

}