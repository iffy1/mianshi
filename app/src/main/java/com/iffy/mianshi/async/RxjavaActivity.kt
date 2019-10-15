package com.iffy.mianshi.async

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R
import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

//20191013

class RxjavaActivity : AppCompatActivity() {
    lateinit var btnMap: Button
    lateinit var btnFlatMap: Button
    lateinit var btnConcat: Button
    lateinit var btnZip: Button
    lateinit var btnInterval: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava)

        btnInterval = findViewById(R.id.btn_rx_interval)
        btnInterval.setOnClickListener {
            rxFlowable()
        }


        btnZip = findViewById(R.id.btn_rx_zip)
        btnZip.setOnClickListener {
            rxZip()
        }

        btnConcat = findViewById(R.id.btn_rx_concat)
        btnConcat.setOnClickListener {
            rxConcat()
        }

        btnMap = findViewById(R.id.btn_rx_map)
        btnMap.setOnClickListener {
            //1)通过 Observable.create() 方法，调用 OkHttp 网络请求；
            //2）通过 map 操作符集合 gson，将 Response 转换为 bean 类；
            rxMap()
        }

        btnFlatMap = findViewById(R.id.btn_rx_flatmap)
        btnFlatMap.setOnClickListener {
            //flatMap 操作符可以将一个发射数据的 Observable 变换为多个 Observables ，
            // 然后将它们发射的数据合并后放到一个单独的 Observable
            rxFlatMap()
        }
    }

    //维持心跳
    private fun rxFlowable() {
        var a = 1
        //onDestroy的时候要取消(fb.dispose()) 不然一直工作
        var fb = Flowable.interval(1, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            //doOnNext方法中保存数据时，确实是在接收到数据前处理的，
            // 但是再准确一些应该是对当前数据做保存数据哦~毕竟是链式调用，
            // 如果在doOnNext之后又掉了其他的操作符，比如map之类的，
            // 那保存的数据和Consumer中接收的数据是不一样滴
            .doOnNext(Consumer {
                println("doOnNext $a Thead:${Thread.currentThread().name}")
                a++
            }).subscribe(Consumer {
                println("subscribe A$a Thead:${Thread.currentThread().name}")
                btnInterval.text = "$a"
                println("subscribe B$a")
            })


    }

    //将两个Observable
    private fun rxZip() {
        val obA = Observable.create(ObservableOnSubscribe<String> {
            Thread.sleep(2500)
            println("got A")
            it.onNext("Hello")

        })

        val obB = Observable.create(ObservableOnSubscribe<String> {
            Thread.sleep(2500)
            println("got B")
            it.onNext("RXjava")

        })

        Observable.zip(obA, obB, object : BiFunction<String, String, String> {
            override fun apply(t1: String, t2: String): String {
                return "$t1 $t2"
            }
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer<String> {
                btnZip.text = it
            })

    }

    private fun rxConcat() {
        var obCache = Observable.create(ObservableOnSubscribe<String> {
            var cache = ""
            if (!TextUtils.isEmpty(cache)) {
                println("got cache")
                it.onNext(cache)
            } else {
                println("cache is empty")
                it.onComplete()
            }
        })

        var obFromNetwork = Observable.create<String> {
            for (i in 1..99) {
                println("geting $i")
                Thread.sleep(20)
            }
            it.onNext("result")
        }

        Observable.concat(obCache, obFromNetwork)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(Consumer {
                btnConcat.text = it
            })


    }

    //flatMap 操作符可以将一个发射数据的 Observable 变换为多个 Observables ，
    // 然后将它们发射的数据合并后放到一个单独的 Observable
    private fun rxFlatMap() {
        var observer = object : Observer<String> {
            override fun onComplete() {
                System.out.println("onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                System.out.println("onSubscribe")
            }

            override fun onNext(t: String) {
                System.out.println("onNext")
                btnFlatMap.text = t
            }

            override fun onError(e: Throwable) {
                System.out.println("onError")
            }
        }

        Observable.create(ObservableOnSubscribe<ArrayList<String>> {
            var array = ArrayList<String>()
            array.add("abc")
            array.add("def")
            array.add("hig")
            it.onNext(array)
            Thread.sleep(5000)
            var arrayb = ArrayList<String>()
            arrayb.add("klm")
            arrayb.add("nop")
            arrayb.add("qrs")
            it.onNext(arrayb)
            //flatMap 将字符串数组拆分成字符串 一对多
        }).flatMap(Function<ArrayList<String>, ObservableSource<String>> { arrya ->
            Observable.create(ObservableOnSubscribe {
                arrya.forEach { s ->
                    s.forEach { c ->
                        Thread.sleep(50)
                        System.out.println("flatMap:$c")
                        it.onNext("$c")
                    }
                }
            })
        })//map 将字符串加后缀 一对一
            .map {
                "$it|"
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            //如果只关心onNext 可以用 Consumer代替obeserver
            .subscribe(Consumer<String>() {
                btnFlatMap.text = it
            }, Consumer<Throwable> {

            })
        //.subscribe(observer)


    }

    fun rxMap() {
        var obver = object : Observer<String> {
            override fun onComplete() {
                System.out.println("onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                System.out.println("onSubscribe")
            }

            override fun onNext(t: String) {
                System.out.println("onNext")
                btnMap.text = t
            }

            override fun onError(e: Throwable) {
                System.out.println("onError")
            }
        }

        Observable.create<ArrayList<String>> {
            var a = ArrayList<String>()
            a.add("abc")
            a.add("edf")
            a.add("ghi")
            it.onNext(a)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            //map 一对一只能返回一个值
            .map(object : Function<ArrayList<String>, String> {
                override fun apply(t: ArrayList<String>): String {
                    var result = ""
                    t.forEach {
                        it.forEach {
                            Thread.sleep(50)
                            System.out.println("$it")
                            result += "$it"
                        }
                    }
                    return result
                }
            }).subscribe(obver)
    }
}