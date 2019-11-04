package com.iffy.mianshi.oom

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.*
import android.util.SparseArray
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.forEach
import com.iffy.mianshi.R
import leakcanary.AppWatcher
import java.lang.ref.WeakReference


class OomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Message.obtain()

        //不用时 需取消注册 不然会持有context
        this.registerReceiver(MyBroadCaster(), IntentFilter())

        //OOM是否能catch
        Thread(Runnable {
            var gallery = SparseArray<Bitmap>()
            var i = 0
            while (i < 100) {
                try {
                    println("OOM添加图片$i")
                    gallery.put(i++, BitmapFactory.decodeResource(resources, R.mipmap.bigpic))
                } catch (e: OutOfMemoryError) {
                    println("发生OOM ${e.message} 开始清理")
                    gallery.forEach { _, item ->
                        item?.recycle()
                    }
                } catch (e: Exception) {
                    println("发生异常 不是OOM  ${e.message}")

                }
                //不管有没有异常，finally中的代码都会执行
                finally {

                }
            }
        })//.start()


        //错误用法，持有activity的引用，导致不能释放
        Thread(Runnable {
            while (true) {
                println("keep working 持有activity 对象")
            }
        }).start()

        //这段使用有警告
        object : AsyncTask<Void, Void, Int>() {
            override fun doInBackground(vararg params: Void?): Int {
                while (true) {
                    println("keep working 持有activity 对象")
                }
                return 0
            }

        }.execute()

        //持有activit引用
        //Android中post()方法可以直接在非UI线程中更新UI
        Thread(Runnable {
            Looper.prepare()
            Handler().post(Runnable {
                while (true) {
                    println("Handler当前线程:${Thread.currentThread().name}")
                    println("keep working 持有activity 对象")
                }
            })
            Looper.loop()
        }).start()


        //弱引用持有activit引用
        Thread(Runnable {
            Looper.prepare()
            MyHandler(this).post(Runnable {
                while (true) {
                    println("MyHandler当前线程:${Thread.currentThread().name}")
                    println("keep working 持有activity 对象")
                }
            })
            Looper.loop()
        }).start()


        //静态内部类（嵌套类）
        MyThread(Runnable {
            while (true) {
                println("keep working 静态内部类(kotlin中叫嵌套类)不持有外部类activity的引用")
            }
        }).start()

        //静态内部类（嵌套类）
        MyAsync().execute()
    }

    //MainActivity存在内存泄漏，原因就是非静态内部类LeakThread持有外部类MainActivity的引用，LeakThread中做了耗时操作，导致MainActivity无法被释放。
    override fun onDestroy() {
        super.onDestroy()
        AppWatcher.objectWatcher.watch(this)
    }

}


class MyBroadCaster : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

    }

}

//在Kotlin中，没有静态内部类一说，Java中的静态内部类在Kotlin中称为嵌套类。
// 而且默认就是嵌套类，也就是内部类不写任何修饰符就是嵌套类。
//同样的，内部类会持有一个外部类的引用，嵌套类不持有外部类的引用。
class MyThread(task: Runnable) : Thread(task)

class MyAsync : AsyncTask<Void, Void, Int>() {
    override fun doInBackground(vararg params: Void?): Int {
        while (true) {
            println("keep working 静态内部类(kotlin中叫嵌套类)不持有外部类activity的引用")
        }
        return 0
    }
}


//我们知道消息队列是在一个Looper线程中不断轮询处理消息，
// 那么当这个Activity退出时消息队列中还有未处理的消息或者正在处理消息，
// 而消息队列中的Message持有mHandler实例的引用，mHandler又持有Activity的引用，
// 所以导致该Activity的内存资源无法及时回收，引发内存泄漏
//正确适用方法：
class MyHandler(context: Context) : Handler() {
    //WeakReference如字面意思，弱引用， 当一个对象仅仅被weak reference（弱引用）指向,
    // 而没有任何其他strong reference（强引用）指向的时候, 如果这时GC运行, 那么这个对象就会被回收，
    // 不论当前的内存空间是否足够，这个对象都会被回收。
    private val reference: WeakReference<*>

    init {
        reference = WeakReference(context)
    }
}
