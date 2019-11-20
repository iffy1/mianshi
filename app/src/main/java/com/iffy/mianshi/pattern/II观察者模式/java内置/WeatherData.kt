package com.iffy.mianshi.pattern.II观察者模式.java内置

import java.util.*


class WeatherData(var temp: Int, var shidu: Int, var pre: Int) : Observable() {


    fun setData(temp: Int, shidu: Int, pre: Int) {
        this.temp = temp
        this.shidu = shidu
        this.pre = pre
        setChanged()
    }


}