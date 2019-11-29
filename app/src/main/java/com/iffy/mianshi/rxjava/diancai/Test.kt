package com.iffy.mianshi.rxjava.diancai

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.util.HalfSerializer.onNext
import java.lang.NullPointerException


fun main() {
    //消费者
    val observable = Observable.create<String> {
        it.onNext("鱼香肉丝")
        it.onNext("宫保鸡丁")
        it.onNext("酸辣汤")
        //it.onError(NullPointerException("adbcde"))
        it.onComplete()
    }
    //厨房
    val observer = object : Observer<String> {
        lateinit var disposable:Disposable
        override fun onComplete() {
            println("onComplete")
        }

        override fun onSubscribe(d: Disposable) {
            disposable = d
            println("onSubscribe")
        }

        override fun onNext(t: String) {
            //宫保鸡丁的时候切断订阅
            if("宫保鸡丁".equals(t)){
                disposable.dispose()
            }
            println("onNext $t")
        }

        override fun onError(e: Throwable) {
            println("onError")
        }
    }
    //多种订阅方法
    observable.subscribe(observer)

//    observable.subscribe{
//        println(it)
//    }

    observable.subscribe({
        println(it)
    },{
        println("on error ${it.message}")
    })

    var consumer = object :Consumer<String>{
        override fun accept(t: String?) {
            println("accept $t")
        }

    }

//  Consumer是简易版的Observer，他有多重重载，可以自定义你需要处理的信息，我这里调用的是只接受onNext消息的方法，
//  他只提供一个回调接口accept，由于没有onError和onCompete，无法再 接受到onError或者onCompete之后，实现函数回调。
//  无法回调，并不代表不接收，他还是会接收到onCompete和onError之后做出默认操作，也就是监听者（Consumer）不在接收
//  Observable发送的消息，下方的代码测试了该效果。

    observable.subscribe(consumer)


}


