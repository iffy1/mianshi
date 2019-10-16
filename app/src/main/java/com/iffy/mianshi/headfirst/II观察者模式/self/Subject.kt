package com.iffy.mianshi.headfirst.II观察者模式.self

interface Subject {
    fun registObserver(ob:Observer)
    fun removeObserver(ob:Observer)
    fun update()

}