package com.iffy.mianshi.pattern.II观察者模式.test


/**
 *     author : iffy
 *     time   : 2020/03/12
 */

fun main(){
    val observable = ImplObservable()

    val implObserverA = ImplObserver()
    val implObserverB = ImplObserver()
    val implObserverC = ImplObserver()

    observable.regist(implObserverA)
    observable.regist(implObserverB)
    observable.regist(implObserverC)

    observable.notifyThem("来活了")

    observable.unregist(implObserverB)

    observable.notifyThem("又来活了")


}