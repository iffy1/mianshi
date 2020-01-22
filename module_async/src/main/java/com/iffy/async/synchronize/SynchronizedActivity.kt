package com.iffy.async.synchronize

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.iffy.async.R
import com.iffy.module_base.BaseActivity


class SynchronizedActivity : BaseActivity() {
    override fun getContentId(): Int {
        return R.layout.activity_multithread
    }

    lateinit var btnA: Button
    lateinit var btnB: Button
    lateinit var blackBoard: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnA = findViewById(R.id.button_multithread_A)
        btnA.setOnClickListener {
            Thread(Runnable {
                gotoworkC()
            }).start()
        }
        btnA.measuredHeight
        btnA.measuredWidth

        btnB = findViewById(R.id.button_multithread_B)
        btnB.setOnClickListener {
            Thread(Runnable {
                gotoworkD()
            }).start()
        }
        blackBoard = findViewById(R.id.tv_multithread_blackboard)
        blackBoard.setMovementMethod(ScrollingMovementMethod.getInstance())
    }

    //先点gotoworkA() 后马上点gotoworkB()
    // SynchronizedActivity::class.java 对象所关联的Monitor被gotoworkA()所持有
    //所以必须等gotoworkA()释放锁后gotoworkB()才能执行
    fun gotoworkA() {
        synchronized(SynchronizedActivity::class.java){
            for (i in 1..100) {
                Thread.sleep(40)
                println("gotoworkA $i ----")
            }
        }
    }

    fun gotoworkB() {
        synchronized(SynchronizedActivity::class.java){
            for (i in 1..100) {
                Thread.sleep(40)
                println("gotoworkB $i ----")
            }
        }
    }


    //先点gotoworkC() 后马上点gotoworkD()
    //this 对象所关联的Monitor没有被gotoworkA()所持有
    //gotoworkB()可以和gotoworkA()同时执行
    fun gotoworkC() {
        synchronized(SynchronizedActivity::class.java){
            for (i in 1..100) {
                Thread.sleep(40)
                println("gotoworkC $i ----")
            }
        }
    }

    fun gotoworkD() {
        synchronized(this){
            for (i in 1..100) {
                Thread.sleep(40)
                println("gotoworkD $i ----")
            }
        }
    }


    //方法同步
    @Synchronized
    fun gotowork() {
        for (i in 1..100) {
            Thread.sleep(40)
            println("$i ----")
        }

        //变量锁
        synchronized(btnA) {
            for (i in 1..100) {
                Thread.sleep(40)
                println("btnA 变量锁 $i")
            }
        }

        //Class类锁
        synchronized(SynchronizedActivity::class.java){
            for (i in 1..100) {
                Thread.sleep(40)
                println("Class类锁 $i")
            }
        }

        //使用的是调用该方法的对象锁 跟方法外@Synchronized不一样 只有他扩号内的模块能同步
//        synchronized(this){
//            for (i in 1..100) {
//                Thread.sleep(40)
//                println("方法的对象锁 $i")
//            }
//        }


        //代码块同步
//        synchronized(0){
//            for (i in 1..100) {
//                Thread.sleep(40)
//                println(i)
//            }
//        }

    }
}