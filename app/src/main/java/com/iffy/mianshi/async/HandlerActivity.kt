package com.iffy.mianshi.async

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R

//handler用途
//1未来做某事
//2线程间通信
//3维持心跳
class HandlerActivity : AppCompatActivity() {
    lateinit var blackBoard: TextView
    var mainHandler = Handler {
        println("handleMessage Current Thread is ${Thread.currentThread().name}")
        blackBoard.text = "${blackBoard.text} \n Current Thread is ${Thread.currentThread().name}"
        Toast.makeText(this, it.obj as String, Toast.LENGTH_LONG).show()
        blackBoard.text = "${blackBoard.text} \n ${it.obj as String}"
        true
    }

    var quenHandler = Handler {
        //拖延显示
        Thread.sleep(500)
        println("收到消息${it.obj as String} handleMessage Current Thread is ${Thread.currentThread().name}")
        blackBoard.text = "${blackBoard.text} \n Current Thread is ${Thread.currentThread().name}"
        //Toast.makeText(this, it.obj as String, Toast.LENGTH_LONG).show()
        blackBoard.text = "${blackBoard.text} \n ${it.obj as String}"
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_handler)
        blackBoard = findViewById(R.id.tv_handler_blackboard)
        blackBoard.setMovementMethod(ScrollingMovementMethod.getInstance())
        //发送一系列msg
        var btnQuenen = findViewById<Button>(R.id.btn_handler_qunn)
        btnQuenen.setOnClickListener {
            blackBoard.text = ""
            Thread(Runnable {
                for (i in 0..50) {
                    var msg = Message.obtain()
                    msg.obj = "$i"
                    Thread.sleep(100)
                    println("发送队列消息$i")
                    quenHandler.sendMessage(msg)
                }
            }).start()
        }


        var btnRunOnUi = findViewById<Button>(R.id.btn_run_on_ui_thread)
        btnRunOnUi.setOnClickListener {
            blackBoard.text = ""
            //为什么不用runOnUIThread代替handler，runOnUIThread本质就是activity自带的handler
            //handler可以实现消息管理
            Thread(Runnable {
                for (i in 1..99) {
                    println("I am working $i")
                    //子线程不能touchUI
                    //blackBoard.text = "${blackBoard.text} \n I am working $i"
                    var msg = Message()
                    msg.obj = "$i"
                    mainHandler.sendMessage(msg)
                    Thread.sleep(50)
                }
                //使用activity.handler
                runOnUiThread {
                    blackBoard.text = "${blackBoard.text} \n Current Thread is ${Thread.currentThread().name}"
                    Toast.makeText(getApplicationContext(), "mainHandler msg", Toast.LENGTH_LONG).show()
                    blackBoard.text = "${blackBoard.text} \n 工作结束"
                }
            }).start()
        }


        var btnSubInSubtoMain = findViewById<Button>(R.id.btn_new_handler_in_sub_thread_run_in_main)
        btnSubInSubtoMain.setOnClickListener {
            blackBoard.text = ""
            Thread(Runnable {
                //强转到主线程 获取主线程的looper，或者说是UI线程的looper
                var inernalHandler = Handler(Looper.getMainLooper()) {
                    blackBoard.text = "${blackBoard.text} \n Current Thread is ${Thread.currentThread().name}"
                    Toast.makeText(getApplicationContext(), "mainHandler msg", Toast.LENGTH_LONG).show()
                    blackBoard.text = "${blackBoard.text} \n ${it.obj as String}"
                    true
                }
                for (i in 1..99) {
                    println("I am working")
                    Thread.sleep(50)
                }
                var msg = Message()
                msg.obj = "工作完成"
                inernalHandler.sendMessage(msg)
            }).start()
        }


        var btnSubInSub = findViewById<Button>(R.id.btn_new_handler_in_sub_thread)
        btnSubInSub.setOnClickListener {
            blackBoard.text = ""
            //子线创建handler程运行
            //子线程中创建handler有意义吗??? 为啥不直接runOnUIThread
            Thread(Runnable {
                Looper.prepare()// 此处获取到当前线程的Looper，并且prepare()
                var hand = Handler() {
                    println("Handlemessage Thread is ${Thread.currentThread().name}")
                    //不能touch主线程 hanldermessage在子线程中运行
                    //blackBoard.text = "${blackBoard.text} \n Current Thread is ${Thread.currentThread().name}"
                    //Toast.makeText(getApplicationContext(), "mainHandler msg", Toast.LENGTH_LONG).show()
                    //blackBoard.text = "${blackBoard.text} \n ${it.obj as String}"
                    true
                }
                println("RUN Thread is ${Thread.currentThread().name}")
                var msg = Message()
                msg.obj = "工作完成"
                hand.sendMessage(msg)
                Looper.loop()//不断遍历MessageQueue中是否有消息
            }).start()
        }


        var btnSendInSub = findViewById<Button>(R.id.btn_handler_in_sub_thread)
        btnSendInSub.setOnClickListener {
            blackBoard.text = ""
            //子线程运行
            Thread(MyRunable()).start()
        }


        var btn_post_delay = findViewById<Button>(R.id.btn_handler_postdelay)
        btn_post_delay.setOnClickListener {
            blackBoard.text = ""
            //主线程运行
            mainHandler.postDelayed(MyRunable(), 5000)
        }

        var btn_post = findViewById<Button>(R.id.btn_handler_post)
        btn_post.setOnClickListener {
            blackBoard.text = ""
            //主线程运行
            mainHandler.post(MyRunable())

        }

    }

    inner class MyRunable : Runnable {
        override fun run() {
            println("run() Working Thread is ${Thread.currentThread().name}")
            //blackBoard.text = "${blackBoard.text} \n Current Thread is ${Thread.currentThread().name}"
            var msg = Message()
            msg.obj = "工作完成"
            mainHandler.sendMessage(msg)
        }

    }
}