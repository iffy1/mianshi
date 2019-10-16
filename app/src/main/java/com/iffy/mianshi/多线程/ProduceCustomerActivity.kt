package com.iffy.mianshi.多线程

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R


class ProduceCustomerActivity : AppCompatActivity() {
    //Kotlin故意没有构建语言的并发性。我们认为这应该由libraries来处理。
    //但是你仍然能够使用java.lang.Object的实例作为lock，并且调用相关的方法。
    private val lock = java.lang.Object()
    lateinit var btnA: Button
    lateinit var btnB: Button
    lateinit var btnStop:Button
    lateinit var blackBoard: TextView
    var keepworking = false
    //生产者与消费者互斥使用仓库 仓库最大容量为10
    val Capbility = 10
    val CustomerCount = 10
    val ProducerCount = 10
    var store = ArrayList<Int>(Capbility)
    var total_produce_count = 0
    var total_consume_count =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multithread_producer)
        btnA = findViewById(R.id.button_multithread_A)
        btnA.setOnClickListener {
            total_produce_count = 0
            total_consume_count =0
            keepworking = true
            //生产者线程
            for (i in 0..ProducerCount) {
                Thread(Runnable {
                    while (keepworking) {
                        synchronized(lock) {
                            //库存大于10的话停止生产
                            while (store.size == Capbility) {
                                lock.notifyAll()// 唤醒等待队列中所有线程
                                lock.wait()//表示当前线程需要在lock上进行等待
                            }
                            store.add(1)
                            total_produce_count++
                            Thread.sleep(20)
                            println("produced $i product, store has ${store.size} products")

                        }
                    }
                }).start()
            }

            //消费者线程
            for (i in 0..CustomerCount) {
                Thread(Runnable {
                    while (keepworking) {
                        synchronized(lock) {
                            //库存小于2的时候停止消费
                            while (store.size == 2) {
                                lock.notifyAll()//库存不足唤醒所有等待的线程
                                lock.wait()//表示当前线程需要在lock上进行等待
                            }
                            store.removeAt(0)
                            total_consume_count++
                            println("costomer $i consume 1 product, store has ${store.size} products")
                        }
                    }
                }).start()
            }
        }

        btnB = findViewById(R.id.button_multithread_B)
        btnB.setOnClickListener {
            total_produce_count = 0
            total_consume_count =0
            keepworking = true
            //生产者线程
            for (i in 0..ProducerCount) {
                Thread(Runnable {
                    while (keepworking) {
                        //库存大于10的话停止生产
                        while (store.size >= Capbility) {
                            Thread.sleep(40)
                        }
                        store.add(1)
                        total_produce_count++
                        Thread.sleep(20)
                        println("produced $i product, store has ${store.size} products")


                    }
                }).start()
            }

            //消费者线程
            for (i in 0..CustomerCount) {
                Thread(Runnable {
                    while (keepworking) {
                        //库存小于2的时候停止消费
                        while (store.size <= 2) {
                            Thread.sleep(40)
                        }
                        store.removeAt(0)
                        total_consume_count++
                        println("costomer $i consume 1 product, store has ${store.size} products")
                    }

                }).start()
            }
        }

        blackBoard = findViewById(R.id.tv_multithread_blackboard)
        blackBoard.setMovementMethod(ScrollingMovementMethod.getInstance())

        btnStop = findViewById(R.id.button_stop)
        btnStop.setOnClickListener {
            keepworking = false
            blackBoard.text = "一共生产了$total_produce_count 个商品\n一共消费了$total_consume_count 个商品\n 仓库还剩${store.size}个商品"
        }
    }


}