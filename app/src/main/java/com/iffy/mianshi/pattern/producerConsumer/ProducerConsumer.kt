package com.iffy.mianshi.pattern.producerConsumer

class ProducerConsumer {
}

private val lock = java.lang.Object()
var store = ArrayList<String>()
var MAX_SIZI = 100
var MIN_SIZI = 5
fun main() {
    Thread(Producer()).start()
    Thread(Producer()).start()
    Thread(Producer()).start()
    Thread(Producer()).start()
    Thread(Producer()).start()
    Thread(Consumer()).start()
    Thread(Consumer()).start()
    Thread(Consumer()).start()
    Thread(Consumer()).start()
    Thread(Consumer()).start()
}


class Producer : Runnable {
    override fun run() {
        while (true) {
            synchronized(lock) {
                if (store.size < MAX_SIZI) {
                    store.add("iffy")
                    println("$this 加仓 ${store.size}")
                } else {
                    lock.notifyAll()
                    lock.wait()
                }

            }
        }
    }
}

class Consumer : Runnable {
    override fun run() {
        while (true) {
            synchronized(lock) {
                if (store.size > MIN_SIZI) {
                    store.removeAt(0)
                    println("$this 减仓 ${store.size}")
                } else {
                    lock.notifyAll()
                    lock.wait()
                }
            }
        }
    }

}








