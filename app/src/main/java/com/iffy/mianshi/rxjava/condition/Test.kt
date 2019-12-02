package com.iffy.mianshi.rxjava.condition


import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.functions.Consumer
import io.reactivex.functions.Predicate
import java.util.concurrent.TimeUnit


fun main() {
    var a = Observable.just(1, "2", 3.0, 4L)
    println("------------3.1 all（）判断发送的每项数据是否都满足 设置的函数条件 若满足，返回 true；否则，返回 false")
    a.all(object : Predicate<Any> {
        override fun test(t: Any): Boolean {
            return t.javaClass.equals(Integer::class.java)
        }
    })
        .subscribe(object : Consumer<Any> {
            override fun accept(t: Any?) {
                println(t)
            }
        })

    println("------------3.2 takeWhile（） 若发送的数据满足该条件，则发送该项数据；否则不发送")
    /*    Cat::class.java和cat.javaClass都表示Class
            person.javaClass.kotlin == Person::class都表示KClass*/
    a.takeWhile(object : Predicate<Any> {
        override fun test(t: Any): Boolean {
            //println(Integer::javaClass)
            //println(Integer::class.java)
            return t::class.java.equals(Integer::class.java)
        }
    }).subscribe(object : Consumer<Any> {
        override fun accept(t: Any?) {
            println("takeWhile $t")
        }

    })

    println("------------3.3 skipWhile（）第一个不满足条件后开始发射剩下所有的")
    Observable.just(1, "2", 3.0, 4L, 6, 7)
        .skipWhile(object : Predicate<Any> {
            override fun test(t: Any): Boolean {
                var skip = t::class.java.equals(Integer::class.java)
                println(skip)
                return skip
            }

        }).subscribe { println(it) }

    println("------------3.4 takeUntil（） A 执行到某个条件时，停止发送事件")
    a.takeUntil(object : Predicate<Any> {
        override fun test(t: Any): Boolean {
            return t::class.java.equals(String::class.java)
        }
    }).subscribe {
        println(it)
    }

    println("------------3.4 takeUntil（） B 即 等到 takeUntil（） 传入的Observable开始发送数据的时候 第一个Observable才停止发送")
    println(System.currentTimeMillis() / 1000)
    Observable.interval(1, TimeUnit.SECONDS)
        .takeUntil(Observable.timer(8, TimeUnit.SECONDS))
        .subscribe { println("${System.currentTimeMillis() / 1000} $it") }
    Thread.sleep(8000)


    println("------------3.5 skipUntil（） 一直跳过第一个发射的东东  直到第二个发射器发射")
    Observable.intervalRange(1, 5, 0, 1, TimeUnit.SECONDS)
        .skipUntil(Observable.timer(3, TimeUnit.SECONDS))
        .subscribe { println(it) }

    Thread.sleep(6000)

    println("------------3.6 SequenceEqual（）判定两个Observables需要发送的数据是否相同")
    var b = Observable.sequenceEqual(
        Observable.just(1, 2, 3),
        Observable.just(1, 2, 3)
    )
        .subscribe(object : Consumer<Boolean> {
            override fun accept(t: Boolean?) {
                if (t!!) {
                    println("这两个东西一样")
                } else {
                    println("这两个东西不一样")
                }
            }
        })

    println("------------3.7 contains（）是否包含这个元素")
    var d = Observable.just(1, 2, 3)
        .contains(2)
        .subscribe(object : Consumer<Boolean> {
            override fun accept(contain: Boolean?) {
                if (contain!!) {
                    println("第一个包含第二个")
                } else {
                    println("第一个不包含第二个")
                }
            }

        })

    println("------------3.8 isEmpty（）")
    a.isEmpty.subscribe(Consumer<Boolean> {
        println("他是空的吗？ $it")
    })


    println("------------3.9 amb（）当需要发送多个 Observable时，只发送 先发送数据的Observable的数据，而其余 Observable则被丢弃。")
    var oba = Observable.intervalRange(1, 10, 10, 1, TimeUnit.SECONDS)
    var obb = Observable.intervalRange(10, 10, 5, 1, TimeUnit.SECONDS)
    var obc = Observable.intervalRange(100, 5, 2, 1, TimeUnit.SECONDS)

    var list = ArrayList<Observable<Long>>()
    list.add(oba)
    list.add(obb)
    list.add(obc)

    Observable.amb(list).subscribe { println(it) }
    Thread.sleep(7000)


    println("------------3.10 defaultIfEmpty（）在不发送任何有效事件（ Next事件）、仅发送了 Complete 事件的前提下，发送一个默认值")

    Observable.create(object : ObservableOnSubscribe<Any> {
        override fun subscribe(emitter: ObservableEmitter<Any>) {
            //emitter.onNext(1)
            emitter.onComplete()
        }
    }).defaultIfEmpty(10).subscribe { println(it) }


}