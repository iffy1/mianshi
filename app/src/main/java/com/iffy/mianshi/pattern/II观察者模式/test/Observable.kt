package com.iffy.mianshi.pattern.II观察者模式.test

/**
 *     author : iffy
 *     time   : 2020/03/12
 */
interface Observable {
    fun regist(observer: Observer)
    fun unregist(observer: Observer)
    fun notifyThem(s:String)
}