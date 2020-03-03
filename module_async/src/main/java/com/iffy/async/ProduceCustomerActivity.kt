package com.iffy.async

import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.iffy.module_base.BaseActivity


//什么是生产者消费者模式
//生产者消费者模式是通过一个容器来解决生产者和消费者的强耦合问题。
// 生产者和消费者彼此之间不直接通讯，而通过阻塞队列来进行通讯，所以生产者生产完数据之后不用等待消费者处理，
// 直接扔给阻塞队列，消费者不找生产者要数据，而是直接从阻塞队列里取，
// 阻塞队列就相当于一个缓冲区，平衡了生产者和消费者的处理能力。
class ProduceCustomerActivity : BaseActivity() {
    override fun getContentId(): Int {
        return  R.layout.activity_multithread_producer
    }

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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                            //Thread.sleep(20)
                            println("Producer $i produce, store has ${store.size} products")

                        }
                    }
                }).start()
            }

            //消费者线程
            for (i in 0..CustomerCount) {
                Thread(Runnable {
                    while (keepworking) {
                        synchronized(lock) {
                            //库存小于1的时候停止消费
                            while (store.size <= 1) {
                                lock.notifyAll()//库存不足唤醒所有等待的线程
                                lock.wait()//表示当前线程需要在lock上进行等待
                            }
                            store.removeAt(0)
                            total_consume_count++
                            println("costomer $i consume 1 Product, store has ${store.size} products")
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
                        println("producer $i , store has ${store.size} products")


                    }
                }).start()
            }

            //消费者线程
            for (i in 0..CustomerCount) {
                Thread(Runnable {
                    while (keepworking) {
                        //库存小于1的时候停止消费
                        while (store.size <= 1) {
                            Thread.sleep(40)
                        }
                        store.removeAt(0)
                        total_consume_count++
                        println("costomer $i consume 1 Product, store has ${store.size} products")
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