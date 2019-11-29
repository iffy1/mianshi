package com.iffy.mianshi.rxjava.create

import android.os.SystemClock
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import java.lang.NullPointerException
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

fun main() {
    println("-----------------------3.1 基本创建 最基本的创建方法:------------------------------")
    Observable.create(object : ObservableOnSubscribe<String> {
        override fun subscribe(emitter: ObservableEmitter<String>) {
            emitter.onNext("锅包肉")
            emitter.onComplete()
        }
    }).subscribe({ println(it) })

    println("--------------3.2 快速创建 & 发送事件 快速创建 Just 最多只能发送10个参数:--------------------")
    Observable.just("1鱼香肉丝", "2三杯鸡", "3", "4", "5", "6", "7", "8", "9", "10")
        .subscribe({ println(it) }, { println(it) }, { println("complete") })

    println("FromArray 传入数组一次性发送，一次接收所有元素")
    //注意这里要加星号 不然 会直接把整个数组传递过去
    //为了将数组展开并传入可变参数，Kotlin使用星号（*）操作符将数组进行展开：
    //如果在某些场景中，你需要将数组展开并传入到可变参数中，这很有用！
    var data = arrayOf("宫保鸡丁","京酱肉丝")
    Observable.fromArray(*data).subscribe { println(it) }

    //整体传递
    Observable.fromArray(data).subscribe {it.forEach {println(it) } }

    println("fromIterable:")
    Observable.fromIterable(object : Iterable<String> {
        override fun iterator(): Iterator<String> {
            return arrayOf("虹鳟鱼", "清江鱼", "甲鱼").iterator()
        }
    }).subscribe { println(it) }

    println("下面仅用于测试 empty 只调用oncomplete:")
    Observable.empty<String>().subscribe(object : Observer<String> {
        override fun onComplete() {
            println("onComplete")
        }

        override fun onSubscribe(d: Disposable) {
            println("onSubscribe")
        }

        override fun onNext(t: String) {
            println("onNext")
        }

        override fun onError(e: Throwable) {
            println("onError")
        }
    })

    println("Error 仅发送Error事件:")
    Observable.error<String>(NullPointerException("空指针了"))
        .subscribe({ println("onNext") }, { println("onerror ${it.message}") })

    println("never 不发送任何事件:")
    Observable.never<String>().subscribe { println("onnext: $it") }

    println("------------------------3.3 延迟创建--------------------------------------------")
    println("应用场景:\n" +
            "动态创建被观察者对象（Observable） & 获取最新的Observable对象数据" +
            "“defer（）”直到有观察者（Observer ）订阅时，才动态创建被观察者对象（Observable） & 发送事件\n" +
            "每次订阅后，都会得到一个刚创建的最新的Observable对象，这可以确保Observable对象里的数据是最新的\n")
    var observable = Observable.defer(object: Callable<ObservableSource<String>>{
        override fun call(): ObservableSource<String> {
            println("创建observerableB 订阅的时候创建 保证订阅时数据是最新的")
            //可以在这里获取最新的数据 网络、db
           return Observable.just("喝汤","吃鸡")
        }
    })
    println("创建observerableA $observable")
    Thread.sleep(2000)
    println("订阅observerableA")
    observable.subscribe{ println(it)}




    println("timer() 本质 = 延迟指定时间后，调用一次 onNext(0)${System.currentTimeMillis()/1000}")
    Observable.timer(2,TimeUnit.SECONDS).subscribe({ println("timer() onNext $it ${System.currentTimeMillis()/1000}")},{},{ println("onComplete")},{println("timer() onSubscripe ${System.currentTimeMillis()/1000}")})
    //等待一会不然 程序结束后就没办法调用onNext了
    Thread.sleep(3000)


    println("interval() 间歇 发送的事件序列 = 从0开始、无限递增1的的整数序列")
    Observable.interval(2,TimeUnit.SECONDS).subscribe({ println("interval() onNext $it")},{},{ println("interval() onComplet")},{ println("interval() onSubscribe")})
    Thread.sleep(10000)

    println("intervalRange（）   参数1 = 事件序列起始点；参数2 = 事件数量； 参数3 = 第1次事件延迟发送时间；参数4 = 间隔时间数字； 参数5 = 时间单位")
    Observable.intervalRange(10,5,1,1,TimeUnit.SECONDS).subscribe({ println("intervalRange() onNext $it")},{},{ println("intervalRange() onComplet")},{ println("intervalRange() onSubscribe")})
    Thread.sleep(10000)

    println("Range 连续发送 1个事件序列，可指定范围")
    Observable.range(0,100).subscribe({ println("Range() onNext $it")},{},{ println("Range() onComplet")},{ println("Range() onSubscribe")})
    Thread.sleep(3000)

    println("Range Long")
    Observable.rangeLong(1,100).subscribe{ println("onNext $it")}
}


