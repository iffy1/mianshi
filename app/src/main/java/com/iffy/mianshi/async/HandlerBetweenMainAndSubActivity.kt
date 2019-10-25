package com.iffy.mianshi.async

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R

class HandlerBetweenMainAndSubActivity : AppCompatActivity() {
    lateinit var blackboard: TextView
    //主线程的handler
    var mMainHandler = Handler() {
        var msg = it.obj as String
        blackboard.text = "${blackboard.text} $msg"
        true
    }

    lateinit var subHandlerA: Handler
    lateinit var subHandlerB: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_main_sub)
        blackboard = findViewById(R.id.tv_handler_blackboard)

        val btn_sub_main = findViewById<Button>(R.id.btn_sub_to_main)
        btn_sub_main.setOnClickListener {
            Thread(Runnable {

                val msg = Message()
                msg.obj = "aaa"
                //使用主线程mMainHandler发送消息
                mMainHandler.sendMessage(msg)

                Looper.prepare()
                subHandlerA = Handler {
                    println("subHandlerA 收到消息 ${it.obj as String}")
                    //转手传给主线程的handler(不能直接把it传给主线程 会报异常)
                    //使用obtain复制一个出来
                    var b = Message.obtain(it)
                    mMainHandler.sendMessage(b)
                    true
                }
                //这里需要注意，当线程loop起来是时，线程就一直在循环中。就是说Looper.loop()后面的代码就不能被执行了。想要执行，需要先退出loop。
                //当调用了Looper.loop()之后，线程就就会被一个for(;;)死循环阻塞
                Looper.loop()



            }).start()
        }

        val btn_sub_sub = findViewById<Button>(R.id.btn_sub_to_sub)
        btn_sub_sub.setOnClickListener {
            Thread(Runnable {
                //从子线程2发送给子线程1
                val msg = Message()
                msg.obj = "bbb"
                subHandlerA.sendMessage(msg)


                Looper.prepare()
                subHandlerB = Handler {
                    println("subHandlerB 收到消息 ${it.obj as String}")
                    true
                }
                Looper.loop()

            }).start()

        }


    }
}