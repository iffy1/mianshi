package com.iffy.mianshi.rxjava.create

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

fun main() {
    val items = arrayOf(0, 10, 20, 31, 45)
    //注意这里要加星号 不然 会直接把整个数组传递过去
    //为了将数组展开并传入可变参数，Kotlin使用星号（*）操作符将数组进行展开：
    //如果在某些场景中，你需要将数组展开并传入到可变参数中，这很有用！
    Observable.fromArray(*items)
        .subscribe(object :Observer <Int>{
            override fun onNext(t: Int) {
                println("onNext $t")
            }
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
            }
        })

    Observable.fromArray(items)
        .subscribe(object :Observer <Array<Int>>{
            override fun onNext(t: Array<Int>) {
                println(t)
            }
            override fun onComplete() {
            }
            override fun onSubscribe(d: Disposable) {
            }
            override fun onError(e: Throwable) {
            }
        })
}
