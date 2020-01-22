package com.iffy.async.pureJava

import java.util.concurrent.ArrayBlockingQueue

//ArrayBlockingQueue是采用数组实现的有界阻塞线程安全队列。
// 如果向已满的队列继续塞入元素，将导致当前的线程阻塞。如果向空队列获取元素，那么将导致当前线程阻塞。

var store = ArrayBlockingQueue<Food>(10)
fun main() {

    for (i in 1..5) {
        customer(i)
    }

    for (i in 1..5) {
        cook(i)
    }

}

fun customer(i: Int) {
    Thread(Runnable {
        while (true) {
            try {
                store.take()
                println("生产者 $i 生产, 仓库有 ${store.size} 个产品")
            } catch (e: Exception) {
            }
        }

    }).start()
}

fun cook(i: Int) {
    Thread(Runnable {
        while (true) {
            try {
                store.add(Food("rice"))
                println("消费者 $i 消费一个产品, 仓库有 ${store.size} 个产品")
            } catch (e: Exception) {
            }
        }
    }).start()
}

class Food(var name: String)