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
    lateinit var blackBoard: TextView
    //生产者与消费者互斥使用仓库 仓库最大容量为10
    val Capbility = 10
    var store = ArrayList<Int>(Capbility)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multithread_producer)
        btnA = findViewById(R.id.button_multithread_A)
        btnA.setOnClickListener {
            //生产者线程
            Thread(Runnable {
                while (true) {
                    synchronized(lock) {
                        //库存大于10的话停止生产
                        if (store.size >= Capbility) {
                            lock.notifyAll()// 唤醒等待队列中所有线程
                            lock.wait()//表示当前线程需要在lock上进行等待
                        } else {
                            store.add(1)
                            Thread.sleep(20)
                            println("produced 1 product, store has ${store.size} products")
                        }
                    }
                }
            }).start()

            //消费者线程
            Thread(Runnable {
                while (true) {
                    synchronized(lock) {
                        //库存小于2的时候停止消费
                        if (store.size <= 2) {
                            lock.notifyAll()//库存不足唤醒所有等待的线程
                            lock.wait()//表示当前线程需要在lock上进行等待
                        } else {
                            store.removeAt(0)
                            println("costomer consume 1 product, store has ${store.size} products")
                        }
                    }
                }
            }).start()
        }

        btnB = findViewById(R.id.button_multithread_B)
        btnB.setOnClickListener {
            Thread(Runnable {
                store.clear()
            }).start()
        }
        blackBoard = findViewById(R.id.tv_multithread_blackboard)
        blackBoard.setMovementMethod(ScrollingMovementMethod.getInstance())
    }


}