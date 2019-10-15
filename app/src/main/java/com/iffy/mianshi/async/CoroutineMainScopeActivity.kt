package com.iffy.mianshi.async

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R
import kotlinx.coroutines.*

class CoroutineMainScopeActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_main_scope)
        val btn = findViewById<Button>(R.id.btn_coroutines_co_main_scope)
        btn.setOnClickListener {
            //动画显示是否阻塞主线程
            val btnb = ObjectAnimator.ofInt(btn, "width", 400, 1200)
            btnb.duration = 10000
            btnb.start()
            //多线程一起工作 相差不到10ms
            //2019-10-11 14:46:40.625 8866-8945/com.iffy.mianshi I/System.out: GotA
            //2019-10-11 14:46:40.633 8866-8947/com.iffy.mianshi I/System.out: GotB
            launch {
                System.out.println(Thread.currentThread())
                val a = async { getA() }
                val b = async { getB() }
                btn.text = ("${a.await()}+${b.await()}")
            }
        }
    }

    suspend fun getA(): Int =
        withContext(Dispatchers.IO) {
            delay(3000)
            System.out.println("GotA")
            11
        }

    suspend fun getB(): Int =
        withContext(Dispatchers.IO) {
            delay(3000)
            System.out.println("GotB")
            9
        }


    //最后别忘了在 onDestroy() 中取消协程，通过扩展函数 cancel() 来实现
    //用户离开界面后停止任务
    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}