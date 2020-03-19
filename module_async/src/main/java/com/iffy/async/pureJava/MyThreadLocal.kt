package com.iffy.async.pureJava

//ThreadLocal用来提供线程内的局部变量。这些变量在多线程环境下访问时能够保证各个线程里的变量相对独立于其它线程内的变量，

//普通变量
var normalLocal = 0

fun main() {
    for (i in 1..10) {
        Thread(Runnable {
            ThreadLocal<Int>().set(1)
            //println(mThreadLocal.get())
            for (j in 1..5) {
                var result = ThreadLocal<Int>().get()
                if (result != null) {
                    ThreadLocal<Int>().set(result + 1)
                } else {
                    ThreadLocal<Int>().set(1)
                }
                normalLocal++
                println("我是线程$i 我的ThreadLocal的值是${ThreadLocal<Int>().get()} 我的nomalLocal的值是$normalLocal")
            }
        }).start()
    }
}