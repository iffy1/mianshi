package com.iffy.mianshi.rxjava.transformOperation

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.ObservableSource
import io.reactivex.functions.Function
import java.util.concurrent.TimeUnit


fun main() {
    println("---------------------3.1 Map（）一对一 将被观察者发送的事件转换为任意的类型事件")
    Observable.just(1, 2, 3, 4, 5)
        .map { "数到 $it" }.subscribe { println("onNext $it") }

    println("---------------------3.2 FlatMap（）一对多 作用：将被观察者发送的事件序列进行 拆分 & 单独转换，再合并成一个新的事件序列，最后再进行发送")
    println("例1， 把整形数组拆成 整形数字")
    flatmapSampleOne()

    println("例2， 把大纲一二三 转换为 一1, 一2，一3，二1，二2，二3，三1，三2，三3")
    flatmapSampleTwo()

    println("例3 每个消息发五遍")
    flatmapSampleThree()

    println("例4 采用RxJava基于事件流的链式操作")
    flatmapSampleFour()

    println("例5 研究Function功能")
    println("---------------------3.3 ConcatMap（）与FlatMap（）的 区别在于：" +
            "拆分 & 重新合并生成的事件序列 的顺序 = 被观察者旧序列生产的顺序")
    concatMapSample()

    println("---------------------3.4 Buffer（）定期从 被观察者（Obervable）需要发送的事件中 获取一定数量的事件 & 放到缓存区中，最终发送")
    bufferSampleOne()

    bufferSampleTwo()


}

fun bufferSampleTwo() {
    //100ms 一个消息  1秒10个消息，100个消息需要10秒
    Observable.intervalRange(0, 100, 0, 100, TimeUnit.MICROSECONDS)
        .buffer(3,2)//3个消息取一次 步长为2
        .subscribe { println("onNext $it") }

    Thread.sleep(15000)
}

fun bufferSampleOne() {
    //100ms 一个消息  1秒10个消息，100个消息需要10秒
    Observable.intervalRange(0, 100, 0, 100, TimeUnit.MICROSECONDS)
        .buffer(50)//50个消息取一次
        .subscribe { println("onNext $it") }

    Thread.sleep(10000)
}

fun flatmapSampleOne() {
    var dataA = arrayOf(1, 2, 3)
    var dataB = arrayOf(4, 5, 6)
    var dataC = arrayOf(7, 8, 9)
    Observable.create(object : ObservableOnSubscribe<Array<Int>> {
        override fun subscribe(emitter: ObservableEmitter<Array<Int>>) {
            emitter.onNext(dataA)
            emitter.onNext(dataB)
            emitter.onNext(dataC)
        }
    }).flatMap(object : Function<Array<Int>, ObservableSource<Int>> {
        override fun apply(t: Array<Int>): ObservableSource<Int> {
            var intArray = arrayListOf<Int>()
            t.forEach {
                intArray.add(it)
            }
            return Observable.fromIterable(intArray)
        }
    }).subscribe { println(it) }
}

fun flatmapSampleTwo() {
    Observable.just("一", "二", "三")
        .flatMap {
            var data = ArrayList<String>()
            for (i in 0..3) {
                data.add("$it $i")
            }
            Observable.fromIterable(data)
        }.subscribe { println("onNext $it") }
}

fun flatmapSampleThree() {
    Observable.range(0, 5).flatMap {
        var data = arrayListOf<String>()
        for (i in 1..5) {
            data.add("消息 $i")
        }
        Observable.fromIterable(data)
    }.subscribe { println("onNext $it") }
}

fun flatmapSampleFour() {
    Observable.create(ObservableOnSubscribe<Int> { emitter ->
        emitter.onNext(1)
        emitter.onNext(2)
        emitter.onNext(3)
    }
        // 采用concatMap（）变换操作符
    ).flatMap(object : Function<Int, ObservableSource<String>> {
        override fun apply(t: Int): ObservableSource<String> {
            val list = ArrayList<String>()
            for (i in 0..2) {
                list.add("我是事件 " + t + "拆分后的子事件" + i)
            }
            return Observable.fromIterable(list)
        }
    }).subscribe { println("onNext $it") }
}


fun concatMapSample() {
    val ff = Function<Long, ObservableSource<String>> {
        val a = arrayListOf<String>()
        for (i in 0..10) {
            a.add("$it ----$i")
        }
        Observable.fromIterable(a)
    }


    Observable.intervalRange(0, 10, 0, 1, TimeUnit.SECONDS)
        .concatMap(ff)
        .subscribe { println("onNext $it") }
    Thread.sleep(10000)
}
