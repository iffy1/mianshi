package com.iffy.mianshi.rxjava.filter

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Predicate
import java.util.concurrent.TimeUnit


fun main() {
    println("-----3.1 根据 指定条件 过滤事件")
    println("-----Filter（）作用过滤 特定条件的事件")
    Observable.just(1, 2, 3, 4, 5)
        .filter(Predicate {
            if (it % 2 == 0) {
                true
            } else {
                false
            }
        }).subscribe { println(it) }

    println("\n\n\n-----ofType（）作用 过滤 特定数据类型的数据")
    println(Double::class.java)
    println(5.0.javaClass)
    Observable.just(1, 2L, 3, "4", 5.0, 6.6)
        //.ofType(Integer::class.java)
        //ofType(String::class.java)
        //过滤double要用5.0.javaClass
        .ofType(5.0.javaClass)//.ofType(Double::class.java)不能工作
        .subscribe { println("onNext $it") }

    println("\n\n\n-----skip（）根据顺序 跳过某个事件")
    Observable.just(1, 2L, 3, "4", 5.0, 6.6)
        .skip(2)
        .subscribe { println("onNext $it") }


    println("\n\n\n-----skipLast（）按时间跳过某个事件")
    Observable.intervalRange(1, 10, 0, 1, TimeUnit.SECONDS)
        .skip(1)//跳过第一个
        .skipLast(1, TimeUnit.SECONDS)//跳过最后一秒
        .subscribe { println("onNext $it") }
    //Thread.sleep(10000)

    println("\n\n\n-----distinct（） 过滤事件序列中重复的事件")
    Observable.just(1, 2, 3, 1, 2, 1, 2, 1, 2)
        .distinct()
        .subscribe { println("onNext $it") }

    println("\n\n\n----- distinctUntilChanged（）过滤事件序列中连续重复的事件")
    Observable.just(1, 2, 1, 2, 2, 2, 2)
        .distinctUntilChanged()
        .subscribe { println("onNext $it") }

    println("\n\n\n----- 3.2 根据 指定事件数量 过滤事件")
    println("----- 通过设置指定的事件数量，仅发送特定数量的事件 take（） & takeLast（）")
    var d = Observable.just(1, 2, 3, 4, 5, 6, 7)
        .take(2)//只拿两个
        .subscribe { println(it) }

    d = Observable.just(1, 2, 3, 4, 5, 6, 7)
        .takeLast(2)//只拿最后两个
        .subscribe { println(it) }

    println("\n\n\n----- 3.3 throttleFirst（） 根据 指定时间 过滤事件. 在某段时间内，只发送该段时间内第1次事件 ")
    println("使用场景1段时间内连续点击按钮，但只执行第1次的点击操作")
    Observable.intervalRange(1,20,0,500,TimeUnit.MICROSECONDS)
        .throttleFirst(1500,TimeUnit.MICROSECONDS)//采样点 之前一批的第一个值
        .subscribe { println(it) }
    //Thread.sleep(11000)

    println("\n\n\n----- 3.3 throttleLast（） 根据 指定时间 过滤事件. 在某段时间内，只发送该段时间最后1次事件")
    Observable.intervalRange(1,20,0,500,TimeUnit.MICROSECONDS)
        .throttleLast(1500,TimeUnit.MICROSECONDS)//采样点 之前一批的第一个值
        .subscribe { println(it) }
    Thread.sleep(11000)

    println("\n\n\n----- Sample（） 在某段时间内，只发送该段时间内最新（最后）1次事件")
    Observable.intervalRange(1,20,0,500,TimeUnit.MICROSECONDS)
        .sample(5,TimeUnit.SECONDS)
        .subscribe { println(it) }
    //Thread.sleep(11000)

    println("\n\n\n-----throttleWithTimeout 发送数据事件时，若2次发送事件的间隔＜指定时间，就会丢弃前一次的数据，直到指定时间内都没有新数据发射时才会发送后一次的数据")
    Observable.create(object:ObservableOnSubscribe<Int>{
        override fun subscribe(emitter: ObservableEmitter<Int>) {
            emitter.onNext(1)
            emitter.onNext(2)
            Thread.sleep(600)
            emitter.onNext(3)
            emitter.onNext(4)
            Thread.sleep(600)
            emitter.onNext(5)
            emitter.onNext(6)
            Thread.sleep(600)
            emitter.onNext(7)
            emitter.onNext(8)
        }

    }).throttleWithTimeout(500,TimeUnit.MICROSECONDS)
        .subscribe { println(it) }
    //Thread.sleep(2000)

    println("\n\n\n-----debounce 消除抖动")
    Observable.create(object:ObservableOnSubscribe<Int>{
        override fun subscribe(emitter: ObservableEmitter<Int>) {
            emitter.onNext(1)
            emitter.onNext(2)
            Thread.sleep(600)
            emitter.onNext(3)
            emitter.onNext(4)
            Thread.sleep(600)
            emitter.onNext(5)
            emitter.onNext(6)
            Thread.sleep(600)
            emitter.onNext(7)
            emitter.onNext(8)
        }

    }).debounce(500,TimeUnit.MICROSECONDS)
        .subscribe { println(it) }
    Thread.sleep(2000)

    println("\n\n\n-----3.4 根据 指定事件位置 过滤事件")
    println("firstElement（） / lastElement（） elementAt（） 仅选取第1个元素 / 最后一个元素")
    Observable.just(1,2,3,4,5)
        .firstElement()
        .subscribe { println(it) }

    Observable.just(1,2,3,4,5)
        .lastElement()
        .subscribe { println(it) }

    Observable.just(1,2,3,4,5)
        .elementAtOrError(8)
        .subscribe({
            println(it)
        },{
            println("onError $it")
        })





}