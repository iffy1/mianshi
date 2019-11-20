package com.iffy.mianshi.pattern.II观察者模式.self

class WeatherData(var temp: Int, var shidu: Int, var pre: Int) : Subject {

    private  var observers: ArrayList<Observer> =ArrayList()
    private var needNotify = false
    override fun registObserver(ob: Observer) {
        observers.add(ob)
    }

    override fun removeObserver(ob: Observer) {
        observers.remove(ob)
    }

    override fun update() {
        observers.forEach {
            it.updateData(this)
        }
    }

    fun needNotify(need:Boolean){
        needNotify = need
    }

    fun setData(temp: Int, shidu: Int, pre: Int) {
        this.temp = temp
        this.shidu = shidu
        this.pre = pre
        if(needNotify) update()
    }


}