package com.iffy.async.reenterlock

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import com.iffy.async.R
import com.iffy.module_base.BaseActivity
import java.util.concurrent.locks.ReentrantLock

/**
 * Created on 1/31/2020.
 * @author Iffy Zhang
 */
class ReentrantlockActivity : BaseActivity(), Handler.Callback {
    var canWork = true
    @RequiresApi(Build.VERSION_CODES.N)
    override fun handleMessage(msg: Message): Boolean {
        when (msg.what) {
            1 -> progressbarA.setProgress(progressbarA.progress + 1, true)
            2 -> progressbarB.setProgress(progressbarB.progress + 1, true)
            3 -> progressbarC.setProgress(progressbarC.progress + 1, true)
            4 -> progressbarD.setProgress(progressbarD.progress + 1, true)
            5 -> progressbarE.setProgress(progressbarE.progress + 1, true)

            6 -> progressbarF.setProgress(progressbarF.progress + 1, true)
            7 -> progressbarG.setProgress(progressbarG.progress + 1, true)
            8 -> progressbarH.setProgress(progressbarH.progress + 1, true)
            9 -> progressbarI.setProgress(progressbarI.progress + 1, true)
            10 -> progressbarJ.setProgress(progressbarJ.progress + 1, true)
        }
        return true
    }

    lateinit var progressbarA: ProgressBar
    lateinit var progressbarB: ProgressBar
    lateinit var progressbarC: ProgressBar
    lateinit var progressbarD: ProgressBar
    lateinit var progressbarE: ProgressBar

    lateinit var progressbarF: ProgressBar
    lateinit var progressbarG: ProgressBar
    lateinit var progressbarH: ProgressBar
    lateinit var progressbarI: ProgressBar
    lateinit var progressbarJ: ProgressBar

    var mainHandler = Handler(this)

    //可以实现公平锁
    lateinit var lock: ReentrantLock

    override fun getContentId(): Int {
        return R.layout.activity_reenterlock
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressbarA = findViewById<ProgressBar>(R.id.progressBar1)
        progressbarB = findViewById<ProgressBar>(R.id.progressBar2)
        progressbarC = findViewById<ProgressBar>(R.id.progressBar3)
        progressbarD = findViewById<ProgressBar>(R.id.progressBar4)
        progressbarE = findViewById<ProgressBar>(R.id.progressBar5)

        progressbarF = findViewById<ProgressBar>(R.id.progressBar6)
        progressbarG = findViewById<ProgressBar>(R.id.progressBar7)
        progressbarH = findViewById<ProgressBar>(R.id.progressBar8)
        progressbarI = findViewById<ProgressBar>(R.id.progressBar9)
        progressbarJ = findViewById<ProgressBar>(R.id.progressBar10)

        val btn = findViewById<Button>(R.id.btn_sychronized)
        btn.setOnClickListener {
            Thread(TaskRunnable(1), "线程1").start()
            Thread(TaskRunnable(2), "线程2").start()
            Thread(TaskRunnable(3), "线程3").start()
            Thread(TaskRunnable(4), "线程4").start()
            Thread(TaskRunnable(5), "线程5").start()

        }

        val btnB = findViewById<Button>(R.id.btn_re_entran_fair)
        btnB.setOnClickListener {
            //公平锁
            lock = ReentrantLock(true)
            startReentrant()
        }

        val btnC = findViewById<Button>(R.id.btn_re_entran_no_fair)
        btnC.setOnClickListener {
            //非公平锁
            lock = ReentrantLock(false)
            startReentrant()
        }


    }

    fun startReentrant() {
        Thread(TaskRunnableB(6), "线程6").start()
        Thread(TaskRunnableB(7), "线程7").start()
        Thread(TaskRunnableB(8), "线程8").start()
        Thread(TaskRunnableB(9), "线程9").start()
        Thread(TaskRunnableB(10), "线程10").start()
    }

    override fun onDestroy() {
        super.onDestroy()
        canWork = false
    }

    //Sychronized锁
    fun updateUI(i: Int) {
        synchronized(this) {
            Thread.sleep(50)
            val msg = Message.obtain()
            msg.what = i
            mainHandler.sendMessage(msg)
        }
    }

    //公平锁 等待时间长的线程会优先获取锁
    fun updateUIB(i: Int) {
        lock.lock()
        Thread.sleep(50)
        val msg = Message.obtain()
        msg.what = i
        mainHandler.sendMessage(msg)
        lock.unlock()
    }

    //Sychronized锁
    inner class TaskRunnable(var i: Int) : Runnable {
        override fun run() {
            while (canWork) {
                updateUI(i)
                println(Thread.currentThread().name)
            }
        }
    }

    //Reentrant锁
    inner class TaskRunnableB(var i: Int) : Runnable {
        override fun run() {
            while (canWork) {
                updateUIB(i)
                println(Thread.currentThread().name)
            }
        }
    }


}