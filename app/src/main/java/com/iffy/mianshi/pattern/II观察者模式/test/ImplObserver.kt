package com.iffy.mianshi.pattern.II观察者模式.test

/**
 *     author : iffy
 *     time   : 2020/03/12
 */
class ImplObserver:Observer {
    override fun onMessageReceived(msg: String) {
       println("我收到消息：$msg")
    }
}