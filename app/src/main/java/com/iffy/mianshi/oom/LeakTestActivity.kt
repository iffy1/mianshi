package com.iffy.mianshi.oom

import com.iffy.mianshi.application.MyApplication
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R


class LeakTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val leakThread = LeakThread()
        leakThread.start()
    }

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
    //
    //它用于自动监控Activity执行onDestroy方法之后是否发生内存泄露,当前此例onDestroy加是多余的，这里只是为了方便举例，如果想要监控Fragment，在Fragment中添加如上的onDestroy方法是有用的。
    override fun onDestroy() {
        super.onDestroy()

        val refWatcher = MyApplication.getRefWatcher(this)//1
        refWatcher.watch(this)
    }
}