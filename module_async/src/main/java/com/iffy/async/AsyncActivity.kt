package com.iffy.async

import android.content.Intent
import android.os.*
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.iffy.async.coroutine.CoroutineMainScopeActivity
import com.iffy.module_base.BaseActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.*


import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

import java.lang.ref.WeakReference
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.ObservableOnSubscribe
import kotlin.collections.ArrayList


//异步方法 AsyncTask
//Anko async
//协程 Coroutine 20191011
//uiHandler Thread 20191011
//CallBack 20191011
//RxJava

class AsyncActivity : BaseActivity(), MyCallBack {
    override fun getContentId(): Int {
        return R.layout.activity_async
    }

    //callback回调
    override fun workdone(result: String) {
        System.out.println("Callback 如果在worker中的子线程调用了callback,那么回调也在子线程中${Thread.currentThread().getName()}")
        runOnUiThread {
            System.out.println("Callback 需要切换到主线程中${Thread.currentThread().getName()}")
            btnCallBack.text = result
        }
    }

    lateinit var btnAsync: Button
    lateinit var btnHandler: Button
    lateinit var btnCallBack: Button
    lateinit var btnRx: Button

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //RxJava分解处理
        //RxJava遵循哪个线程产生就在哪个线程消费的原则,如果不分配线程，所有工作都在主线程执行
        btnRx = findViewById(R.id.btn_rx)
        btnRx.setOnClickListener {
            var a = ArrayList<String>()
            a.add("2")
            a.add("3")
            a.add("4")

            Observable.fromArray(a).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

            //被观察者
            var observable = Observable.create<String> {
                System.out.println("subscribe在线程" + Thread.currentThread().name)
                var result: Int
                for (i in 1..99) {
                    Thread.sleep(50)
                    result = i
                    //Emitter(it)是发射器的意思,有三种发射的方法，
                    // 分别是void onNext(T value)、void onError(Throwable error)、void onComplete()，
                    // onNext方法可以无限调用
                    it.onNext("$result")
                }
            }
            //线程调度
            observable = observable.observeOn(AndroidSchedulers.mainThread())//观察者所在线程
            observable = observable.subscribeOn(Schedulers.io())//订阅者所在线程，
            observable = observable.filter {
                //过滤器 处理Emitter分发过来的消息
                System.out.println("filter $it")
                if (it.contains("2")) {
                    return@filter true
                }
                return@filter false
            }
            //数据加工 filer下来的值加个0
            //map适用于一对一转换，当然也可以配合flatmap进行适用
            observable = observable.map {
                System.out.println("map $it")
                "${it}0"
            }

            //flatmap适用于一对多，多对多的场景
            observable = observable.flatMap {
                Observable.just("${it}flat")
            }


            //观察者
            val observer = object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    System.out.println("onSubscribe在线程" + Thread.currentThread().name)
                }

                override fun onNext(value: String) {
                    System.out.println("onNext在线程" + Thread.currentThread().name)
                    System.out.println("onNext $value")
                    btnRx.text = "$value"
                }

                override fun onError(e: Throwable) {
                    System.out.println("onError ${e.message}")
                }

                override fun onComplete() {
                    System.out.println("onComplete")
                }
            }
            observable.subscribe(observer)
        }

        //RxJava异步链式处理
        var btnRxAS = findViewById<Button>(R.id.btn_rx_as)
        btnRxAS.setOnClickListener {
            Observable.create(ObservableOnSubscribe<String> {
                System.out.println("subscribe在线程" + Thread.currentThread().name)
                var result = 0
                for (i in 1..99) {
                    Thread.sleep(50)
                    result = i
                }
                it.onNext("$result")
            }).observeOn(AndroidSchedulers.mainThread())//观察者所在线程
                .subscribeOn(Schedulers.io())//subscribeOn是事件执行的线程，Schedulers.io()是子线程
                .subscribe(object : Observer<String> {
                    override fun onSubscribe(d: Disposable) {
                        System.out.println("onSubscribe在线程" + Thread.currentThread().name)
                    }

                    override fun onNext(value: String) {
                        System.out.println("onNext在线程" + Thread.currentThread().name)
                        System.out.println("onNext $value")
                        btnRxAS.text = "$value"
                    }

                    override fun onError(e: Throwable) {
                        System.out.println("onError")
                    }

                    override fun onComplete() {
                        System.out.println("onComplete")

                    }
                })
        }


        //callback异步
        btnCallBack = findViewById(R.id.btn_cb)
        btnCallBack.setOnClickListener {
            worker(this).startWork()
        }


        //uiHandler Thread
        val handler = Handler {
            btnHandler.text = "${it.arg1}"
            true
        }
        btnHandler = findViewById(R.id.btn_handler)
        btnHandler.setOnClickListener {
            Thread(java.lang.Runnable {
                Thread.sleep(5000)
                var msg = Message()
                msg.arg1 = 999
                handler.sendMessage(msg)
            }).start()
        }

        //AsyncTask
        btnAsync = findViewById(R.id.btn_async)
        btnAsync.setOnClickListener {
            var masync = MyAsyncTask(this)
            masync.execute()
        }

        //Anko
        val btnAnko = findViewById<Button>(R.id.btn_anko)
        btnAnko.setOnClickListener {
            doAsync {
                for (i in 1..100) {
                    Thread.sleep(50)
                    uiThread {
                        btnAnko.text = "$i"
                    }
                }
                uiThread {
                    btnAnko.text = "doAsync done"
                }
            }
        }

        //Global scope 通常用于启动顶级协程，这些协程在整个应用程序生命周期内运行，不会被过早地被取消。
        // 程序代码通常应该使用自定义的协程作用域。直接使用 GlobalScope 的 async 或者 launch 方法是强烈不建议的。
        //GlobalScope 创建的协程没有父协程，GlobalScope 通常也不与任何生命周期组件绑定。
        // 除非手动管理，否则很难满足我们实际开发中的需求。所以，GlobalScope 能不用就尽量不用。
        val btnCoroutineCo = findViewById<Button>(R.id.btn_coroutines_co)
        btnCoroutineCo.setOnClickListener {
            //coroutines协程异步操作注意时间 相差只有16ms
            //2019-10-11 13:18:21.372 5994-6116/com.iffy.mianshi I/System.out: 得到结果A:99
            //2019-10-11 13:18:21.388 5994-6112/com.iffy.mianshi I/System.out: 得到结果B:99
            //运行在主线程
            GlobalScope.launch(Dispatchers.Main) {
                //异步执行
                val deferredA = async(Dispatchers.IO) {
                    "${getA()}"
                }
                val deferredB = async(Dispatchers.IO) {
                    "${getB()}"
                }
                btnCoroutineCo.text = deferredA.await() + deferredB.await()
                Toast.makeText(this@AsyncActivity, "GlobalScope", Toast.LENGTH_SHORT).show()
            }
        }

        val btnCoroutineSy = findViewById<Button>(R.id.btn_coroutines_sy)
        btnCoroutineSy.setOnClickListener {
            //coroutines协程同步操作注意时间 相差只有1000ms+
            //2019-10-11 13:31:11.493 7440-7626/com.iffy.mianshi I/System.out: 得到结果A:99
            //2019-10-11 13:31:12.621 7440-7563/com.iffy.mianshi I/System.out: 得到结果B:99
            //运行在主线程
            GlobalScope.launch(Dispatchers.Main) {
                //同步顺序执行
                val deferredA = getA()
                val deferredB = getB()
                btnCoroutineSy.text = "$deferredA + $deferredB"
                Toast.makeText(this@AsyncActivity, "GlobalScope", Toast.LENGTH_SHORT).show()
            }
        }

        val btnOpenMainScope = findViewById<Button>(R.id.btn_open_mainScope)
        btnOpenMainScope.setOnClickListener {
            var i = Intent()
            i.setClass(this, CoroutineMainScopeActivity::class.java)
            startActivity(i)
        }

    }

    //Callback的worker类
    class worker(cb: MyCallBack) {
        var cb = cb
        fun startWork() {
            var result = 0
            Thread(java.lang.Runnable {
                for (i in 1..99) {
                    Thread.sleep(20)
                    result += i
                }
                cb.workdone("$result")
            }).start()
        }
    }


    //在 get 函数的方法体内使用 withContext(Dispatchers.IO) 定义一段代码块，
    // 这个代码块将在调度器 Dispatchers.IO 中运行。方法块中的任何代码总是会运行在 IO 调度器中。
    // 由于 withContext 本身就是一个挂起函数，所以它通过协程提供了主线程安全。
    private suspend fun getA(): Int =
        // Dispatchers.IO
        withContext(Dispatchers.IO) {
            var resultGot = 0
            // Dispatchers.IO
            /* perform blocking network IO here */
            for (i in 1..99) {
                delay(10)
                resultGot = i
            }
            System.out.println("得到结果A:$resultGot")
            return@withContext resultGot
        }

    private suspend fun getB(): Int =
        // Dispatchers.IO
        withContext(Dispatchers.IO) {
            var resultGot = 0
            // Dispatchers.IO
            /* perform blocking network IO here */
            for (i in 1..99) {
                delay(10)
                resultGot = i
            }
            System.out.println("得到结果B:$resultGot")
            return@withContext resultGot
        }


    class MyAsyncTask(c: AsyncActivity) : AsyncTask<String, Int, String>() {
        //弱引用 MyAsyncTask不能直接访问activity中的btn
        var context = WeakReference<AsyncActivity>(c)

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onProgressUpdate(vararg values: Int?) {
            context.get()?.btnAsync?.text = "${values.get(0)}"
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            context.get()?.btnAsync?.text = result
            Toast.makeText(context.get(), "MyAsyncTask onPostExecute", Toast.LENGTH_LONG).show()
        }

        override fun doInBackground(vararg params: String?): String {
            for (i in 1..100) {
                Thread.sleep(50)
                publishProgress(i)
            }
            return "AsyncTask done"
        }
    }
}