package com.example.myapplication

import com.example.myapplication.ProducerConsumer.Companion.MAX_SIZE
import com.example.myapplication.ProducerConsumer.Companion.store

fun main() {

    Thread(ProducerB()).start()
    Thread(ProducerB()).start()
    Thread(Producer()).start()
    Thread(Producer()).start()
    Thread(ProducerB()).start()
    Thread(ProducerB()).start()
    Thread(Producer()).start()
    Thread(Producer()).start()
    Thread(ProducerB()).start()
    Thread(ProducerB()).start()
    Thread(Producer()).start()
    Thread(Producer()).start()

    Thread(Consumer()).start()
    Thread(Consumer()).start()
    Thread(Consumer()).start()
//    Thread(Consumer()).start()
//    Thread(Consumer()).start()
//    Thread(Consumer()).start()
//    Thread(Consumer()).start()
//    Thread(Consumer()).start()


}

class ProducerConsumer {
    companion object {
        val lock = java.lang.Object()
        var store = ArrayList<Product>()
        var MAX_SIZE = 10
        var MIN_SIZE = 5
    }
}

class Product(var name: String, var sex: String)


//生产者一直生产到上限 唤醒其他线程
class ProducerB : Runnable {
    override fun run() {
        while (true) {
            synchronized(ProducerConsumer.lock) {
                println("+++++++++++++++++++++++++++++++++++AAAAAA")
                var a = false
                if (store.size < ProducerConsumer.MAX_SIZE) {
                    // println("$this 在干活")
                    if (a) {
                        store.add(Product("狗", "男"))
                        println("${Thread.currentThread().name} +++++++AAAAAA 生产狗男")
                    } else {
                        store.add(Product("猫", "女"))
                        println("${Thread.currentThread().name} +++++++AAAAAA 生产猫女")
                    }
                    a = !a
                }
                ProducerConsumer.lock.notifyAll()
                ProducerConsumer.lock.wait()
            }
        }
    }
}


//生产者交换生产 生产完一个就唤醒其他线程
class Producer : Runnable {
    override fun run() {
        while (true) {
            synchronized(ProducerConsumer.lock) {
                println("+++++++++++++++++++++++++++++++++++BBBBBB")
                var a = false
                if (store.size < ProducerConsumer.MAX_SIZE) {
                    // println("$this 在干活")
                    if (a) {
                        store.add(Product("狗", "男"))
                        println("${Thread.currentThread().name} +++++BBBBBB生产狗男")
                    } else {
                        store.add(Product("猫", "女"))
                        println("${Thread.currentThread().name} +++++BBBBBB生产猫女")

                    }
                    a = !a
                }
                ProducerConsumer.lock.notifyAll()
                ProducerConsumer.lock.wait()

            }
        }
    }
}

class Consumer : Runnable {
    override fun run() {
        while (true) {
            synchronized(ProducerConsumer.lock) {
                println("------------------------------------------")
                while (store.size >= ProducerConsumer.MIN_SIZE) {
                    if(store.size> MAX_SIZE){
                        throw Exception("做多了")
                    }
                    ProducerConsumer.store.removeAt(0)
                    println("consumer 仓库还有  ${store.size}")
                }
                //println("consumer 等待,store.size: ${ProducerConsumer.store.size}")
                ProducerConsumer.lock.notifyAll()
                ProducerConsumer.lock.wait()

            }
        }
    }

}






