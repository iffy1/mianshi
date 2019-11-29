package com.iffy.mianshi.rxjava.merge

import android.os.SystemClock
import io.reactivex.Observable
import io.reactivex.Observable.*
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.functions.BiConsumer
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function3
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit


fun main() {
    println("-------3.1 组合多个被观察者 concat（） / concatArray（）组合多个被观察者一起发送数据，合并后 按发送顺序串行执行")
    concatSample()

    println("-----3.1 组合多个被观察者 merge（） / mergeArray（）组合多个被观察者一起发送数据，合并后 按时间线并行执行")
    mergeSample()

    println("-----concatDelayError（） / mergeDelayError（）第1个被观察者的Error事件将在第2个被观察者发送完事件后再继续发送")
    concatDelayErrorSample()

    println("-----3.2 Zip（） 合并多个事件 合并 多个被观察者（Observable）发送的事件，生成一个新的事件序列（即组合过后的事件序列），并最终发送")
    println("事件组合方式 = 严格按照原先事件序列 进行对位合并 最终合并的事件数量 = 多个被观察者（Observable）中数量最少的数量")
    zipSample()

    println("----- combineLatest（）当两个Observables中的任何一个发送了数据后，将先发送了数据的Observables 的最新（最后）一个数据 与 另外一个Observable发送的每个数据结合，最终基于该函数的结果发送数据")
    println("看到网上绝大部分都是用 combineLatest 来做Android表单的校验, 总感觉有点大材小用. 如只有表单都只有值了了, 提交按钮才可用")
    combineLatestSample()

    println("----- reduce（）把被观察者需要发送的事件聚合成1个事件 & 发送")
    reduceSample()

    println("----- collect（）将被观察者Observable发送的数据事件收集到一个数据结构里")
    collectSample()

    println("----- 3.3 发送事件前追加发送事件 startWith（） / startWithArray（）")
    Observable.just(1,2,3,4).startWith(0).subscribe { println(it) }

    println("----- 3.4 统计发送事件数量count（）作用  统计被观察者发送事件的数量")
    Observable.just(1,1,1,1,1,1)
        .count()
        .subscribe(Consumer { println(it)})



}

fun collectSample() {
    Observable.just(1, 2, 3, 4, 5, 6)
        .collect(object : Callable<ArrayList<Int>> {
            override fun call(): ArrayList<Int> {
                return ArrayList()
            }
        }, object : BiConsumer<ArrayList<Int>, Int> {
            override fun accept(t1: ArrayList<Int>, t2: Int?) {
                if(t2!=null) t1.add(t2)
            }
        }).subscribe (Consumer {
            it.forEach {
                    num ->print(num)
            }

        })
}

fun reduceSample() {
    Observable.just(1, 2, 3, 4, 5)
        .reduce(object : BiFunction<Int, Int, Int> {
            override fun apply(t1: Int, t2: Int): Int {

                println("两个两个的累加t1 $t1")
                println("两个两个的累加t2 $t2")
                return t1 + t2
            }
        }).subscribe { println(it) }
}

fun combineLatestSample() {
    println(System.currentTimeMillis() / 1000)
    Observable.combineLatest(
        //intervalRange(0,5,0,500,TimeUnit.MICROSECONDS),
        just(1L, 2L, 3L, 4L, 5L),
        timer(3, TimeUnit.SECONDS),
        object : BiFunction<Long, Long, String> {
            override fun apply(t1: Long, t2: Long): String {
                println(System.currentTimeMillis() / 1000)
                return "$t1 --- $t2"
            }
        }).subscribe {
        println(System.currentTimeMillis() / 1000)
        println(it)
    }

    Thread.sleep(5000)
}

fun zipSample() {
    val ob1 = just("张三", "李四", "王五")
    val ob2 = just("鱼香肉丝", "宫保鸡丁")
    val ob3 = just(1, 2, 3)
    Observable.zip(ob1, ob2, ob3, object : Function3<String, String, Int, String> {
        override fun apply(t1: String, t2: String, t3: Int): String {
            return "$t1 $t2 $t3"
        }
    }).subscribe { println(it) }

    Observable.zip(ob1, ob2, object : BiFunction<String, String, String> {
        override fun apply(t1: String, t2: String): String {
            return "$t1 $t2"
        }
    }).subscribe { println(it) }
}

fun concatDelayErrorSample() {
    Observable.mergeDelayError(
        Observable.create<Int> {
            it.onNext(1)
            it.onNext(2)
            it.onNext(3)
            it.onError(NullPointerException("异常了"))//如果不用mergeDelayError，下面的4,5,6将不会发送
            it.onComplete()
        },
        just(4, 5, 6)
    ).subscribe({ println(it) },
        { println("onerror") },
        { println("Oncomplete") },
        { println("onSub") })
}

fun mergeSample() {
    Observable.merge(
        intervalRange(0, 3, 0, 1, TimeUnit.SECONDS),
        intervalRange(4, 3, 0, 1, TimeUnit.SECONDS),
        intervalRange(7, 3, 0, 1, TimeUnit.SECONDS)
    ).subscribe { println(it) }
    Thread.sleep(10000)

    Observable.merge(just("A","B","C"),
        just(1,2,3),
        just("G","H","I")
    ).subscribe { println(it) }
}

fun concatSample() {
    Observable.concat(
        just("A", "B", "C"),
        just("D", "E", "F"),
        Observable.defer(object : Callable<Observable<String>> {
            override fun call(): Observable<String> {
                return just("G", "H", "I")
            }
        })
    ).subscribe {
        println("$it")
    }

    Observable.concatArray(fromArray(*(arrayOf(1, 2, 3)), *(arrayOf(4, 5, 6)), *(arrayOf(7, 8, 9))))
        .subscribe { println(it) }
}
