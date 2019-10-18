package com.iffy.mianshi.多线程

//ThreadLocal用来提供线程内的局部变量。这些变量在多线程环境下访问时能够保证各个线程里的变量相对独立于其它线程内的变量，
var mThreadLocal = ThreadLocal<Int>()

//普通变量
var normalLocal = 0


fun main() {
    mThreadLocal.set(1)
    println(mThreadLocal.get())
    for (i in 1..10) {
        Thread(Runnable {
            for (j in 1..5) {
                var result = mThreadLocal.get()
                if (result != null) {
                    mThreadLocal.set(result + 1)
                } else {
                    mThreadLocal.set(1)
                }
                normalLocal++
                println("我是线程$i 我的ThreadLocal的值是${mThreadLocal.get()} 我的nomalLocal的值是$normalLocal")
            }
        }).start()
    }
}