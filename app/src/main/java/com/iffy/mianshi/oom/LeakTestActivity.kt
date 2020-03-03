package com.iffy.mianshi.oom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R
import leakcanary.LeakCanary


class LeakTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("LeakTestActivity", "onCreate")
        setContentView(R.layout.activity_main)
        LeakThread().start()
        LeakCanary
    }

    //测试内部类持有外部类对象
    internal inner class LeakThread : Thread() {
        override fun run() {
            try {
                this@LeakTestActivity
                Thread.sleep((6 * 60 * 1000).toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    //MainActivity存在内存泄漏，原因就是非静态内部类LeakThread持有外部类MainActivity的引用，
    // LeakThread中做了耗时操作，导致MainActivity无法被释放。
    override fun onDestroy() {
        Log.e("LeakTestActivity", "onDestroy")
        super.onDestroy()
    }
}