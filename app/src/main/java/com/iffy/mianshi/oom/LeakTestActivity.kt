package com.iffy.mianshi.oom

import com.iffy.mianshi.application.MyApplication
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R
import leakcanary.AppWatcher


class LeakTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val leakThread = LeakThread()
        leakThread.start()
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

    //MainActivity存在内存泄漏，原因就是非静态内部类LeakThread持有外部类MainActivity的引用，LeakThread中做了耗时操作，导致MainActivity无法被释放。
    override fun onDestroy() {
        super.onDestroy()
        AppWatcher.objectWatcher.watch(this)
    }
}