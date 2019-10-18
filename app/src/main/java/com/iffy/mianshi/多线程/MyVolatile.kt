package com.iffy.mianshi.多线程

//Java语言提供了volatile和synchronized两个关键字来保证线程之间操作的有序性，
// volatile是因为其 本身包含禁止指令重排序 的语义，
// synchronized是由 一个变量在同一个时刻只允许一条线程对其进行 lock 操作 这条规则获得的，
// 此规则决定了持有同一个对象锁的两个同步块只能串行执行。



//一旦一个共享变量被volatile修饰之后，那么就具备了两层语义：
//1. 保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
//2. 禁止进行指令重排序。

@Volatile var a = 1


fun main() {
    Thread(Runnable {
        println("Thread A 拿到a了 他的值是$a")
        Thread.sleep(5000)
        a++
        println("Thread A 把a的值加1 他的值是$a Thread A 不需要a了")
    }).start()

    Thread(Runnable {
        Thread.sleep(1000)
        println("Thread B 拿到a了 他的值是$a")
        a++
        println("Thread B 把a的值加1 他的值是$a Thread B 不需要a了")
    }).start()


}


