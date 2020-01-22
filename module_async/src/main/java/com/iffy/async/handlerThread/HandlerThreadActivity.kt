package com.iffy.async.handlerThread

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.iffy.async.R
import com.iffy.module_base.BaseActivity


//HandlerThread 主要作用：方便地实现每隔几秒更新数据的功能，如价格，图片等。比Timer使用方便并且内存占用低。
class HandlerThreadActivity : BaseActivity() {
    override fun getContentId(): Int {
      return R.layout.activity_handler_thread
    }

    var handlerThread = HandlerThread("心跳")
    lateinit var handler: Handler
    lateinit var blackBoard: TextView
    var keepTiao = true

    lateinit var myHandler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handlerThread.start()

        blackBoard = findViewById(R.id.tv_handler_blackboard)
        blackBoard.setMovementMethod(ScrollingMovementMethod.getInstance())


        var btn_stop = findViewById<Button>(R.id.btn_handler_thread_stop)
        btn_stop.setOnClickListener {
            keepTiao = false
        }

        var btn = findViewById<Button>(R.id.btn_handler_thread)
        btn.setOnClickListener {
            useHandlerThread()
        }

        var btnb = findViewById<Button>(R.id.btn_handler_new_subThread)
        btnb.setOnClickListener {
            useMyThread()
        }


    }

    private fun useMyThread() {
        Thread(Runnable {
            Looper.prepare()
            myHandler = Handler {
                updateInUIThread()
                if (keepTiao) {
                    myHandler.sendEmptyMessageDelayed(0, 1000)
                }
                true
            }
            myHandler.sendEmptyMessage(0)
            Looper.loop()
        }).start()
    }

    private fun useHandlerThread() {
        keepTiao = true
        blackBoard.text = ""
        handler = Handler(handlerThread.looper) {
            //耗时操作
            println("Received msg")
            println("Current Thread is ${Thread.currentThread().name}")
            Thread.sleep(5000)
            if (keepTiao) {
                handler.sendEmptyMessage(1)
            }
            updateInUIThread()
            true
        }

        handler.sendEmptyMessage(1)
    }

    fun updateInUIThread() {
        runOnUiThread {
            blackBoard.text = "${blackBoard.text} \n Current Thread is ${Thread.currentThread().name}"
            //行数*行高
            val offset = blackBoard.getLineCount() * blackBoard.getLineHeight()
            //文字总高度大于TextView的高度
            if (offset > blackBoard.height)
                blackBoard.scrollTo(0, offset - blackBoard.height)
        }
    }
}