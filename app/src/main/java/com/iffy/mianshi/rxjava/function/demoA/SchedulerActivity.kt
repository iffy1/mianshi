package com.iffy.mianshi.rxjava.function.demoA

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


//RxJava中的常见开发应用场景： 线程控制（也称为调度 / 切换），即讲解功能性操作符中的：subscribeOn（） & observeOn（）
//若Observable.observeOn（）多次指定观察者 接收 & 响应事件的线程，则每次指定均有效，即每指定一次，就会进行一次线程的切换
//若Observable.subscribeOn（）多次指定被观察者 生产事件的线程，则只有第一次指定有效，其余的指定线程无效

class SchedulerActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
        test()

    }
}

fun test() {
    val observable = Observable.create(object : ObservableOnSubscribe<Int> {
        override fun subscribe(emitter: ObservableEmitter<Int>) {
            println("observable 当前的线程为 ${Thread.currentThread().name}")
        }
    })

    val observer = object : Observer<Int> {
        override fun onComplete() {
            println("onComplete")
        }

        override fun onSubscribe(d: Disposable) {
            println("observer 当前的线程为 ${Thread.currentThread().name}")
        }

        override fun onNext(t: Int) {
            println("onNext $t")
        }

        override fun onError(e: Throwable) {
            println("onError")
        }
    }

    observable
        .observeOn(AndroidSchedulers.mainThread())  // 2. 指定观察者 接收 & 响应事件的线程
        .subscribeOn(Schedulers.io())// 1. 指定被观察者 生产事件的线程
        .subscribe(observer)
}