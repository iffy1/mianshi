package com.iffy.mianshi.pattern.II观察者模式.test

/**
 *     author : iffy
 *     time   : 2020/03/12
 */
class ImplObservable:Observable {
    val list:ArrayList<Observer> = ArrayList<Observer>()

    override fun regist(observer: Observer) {
        list.add(observer)
    }


    override fun unregist(observer: Observer) {
        list.remove(observer)
    }

    override fun notifyThem(s: String) {
        list.forEach{
            it.onMessageReceived("$it+ $s")
        }

    }
}