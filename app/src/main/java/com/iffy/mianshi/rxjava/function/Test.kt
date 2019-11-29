package com.iffy.mianshi.rxjava.function

import io.reactivex.*
import io.reactivex.disposables.Disposable
import java.lang.NullPointerException
import java.util.concurrent.TimeUnit
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate


fun main() {
    println("3.1 subscribe（） 连接被观察者 & 观察者")
    val observable = Observable.just(1, 2, 3, 4)
    val observer = object : Observer<Int> {
        override fun onComplete() {
            println("onComplete")
        }

        override fun onSubscribe(d: Disposable) {}
        override fun onNext(t: Int) {
            println(t)
        }

        override fun onError(e: Throwable) {
            println("onError")
        }
    }

    observable.subscribe(observer)

    println(
        "\n\n\n3.2 线程调度 快速、方便指定 & 控制被观察者 & 观察者 的工作线程"
    )
    println("由于该部分内容较多 & 重要，所以已独立一篇文章，请看文章：Android RxJava：细说 线程控制（切换 / 调度 ）（含Retrofit实例讲解）")

    println(
        "\n\n\n3.3 延迟操作 即在被观察者发送事件前进行一些延迟的操作 ${System.currentTimeMillis() / 1000}"
    )
    observable.delay(5, TimeUnit.SECONDS)
        .subscribe { println("${System.currentTimeMillis() / 1000} ----$it") }

    Thread.sleep(6000)
    println(
        "\n\n\n3.4 在事件的生命周期中操作 如发送事件前的初始化、发送事件后的回调请求等"
    )
    observable
        .doOnEach { println("doOnEach $it") }
        .doOnNext { println("doOnNext $it") }
        .doAfterNext { println("doAfterNext $it") }
        .subscribe { println("onNext $it") }

    println(
        "\n\n\n3.5 错误处理 发送事件过程中，遇到错误时的处理机制"
    )
    println("onErrorReturn（） 作用 遇到错误时，发送1个特殊事件 & 正常终止")
    Observable.create(object : ObservableOnSubscribe<String> {
        override fun subscribe(emitter: ObservableEmitter<String>) {
            emitter.onNext("A")
            emitter.onError(NullPointerException("空了"))
            emitter.onNext("B")
            emitter.onNext("C")
        }
    })   //出错时发射一个特殊的项并且正常终止，不会调用OnError
        .onErrorReturn(object : Function<Throwable, String> {
            override fun apply(t: Throwable): String {
                return "出错了啊啊啊啊"
            }
        }).subscribe(
            { println("OnNext $it") }
            , { println("OnError $it") }
            , { println("OnComplete") }
            , { println("OnSubscribe") }
        )


    println(
        "\n\n\nonErrorResumeNext（） 作用 遇到错误时，发送1个特殊事件 & 正常终止 \nonErrorResumeNext（）拦截的错误 = Throwable；" +
                "若需拦截Exception请用onExceptionResumeNext（）\n" +
                "若onErrorResumeNext（）拦截的错误 = Exception，则会将错误传递给观察者的onError方法"
    )
    Observable.create(object : ObservableOnSubscribe<String> {
        override fun subscribe(emitter: ObservableEmitter<String>) {
            emitter.onNext("A")
            emitter.onNext("B")
            emitter.onError(NullPointerException("空了"))
            emitter.onNext("C")
        }
    }).onErrorResumeNext(
        // 2. 发生错误事件后，发送一个新的被观察者 & 发送事件序列
        Observable.just("他挂了，我继续")
    )
        .subscribe(
            { println("OnNext $it") }
            , { println("OnError $it") }
            , { println("OnComplete") }
            , { println("OnSubscribe") }
        )

    println("\n\n\nonExceptionResumeNext（）发生异常的话切刀第二个observable")
    Observable.create(object : ObservableOnSubscribe<Int> {
        override fun subscribe(emitter: ObservableEmitter<Int>) {
            emitter.onNext(1)
            emitter.onNext(3 / 0)//这里会产生异常
            emitter.onNext(2)
        }
    }).onExceptionResumeNext(Observable.just(333, 444, 555))
        .subscribe(
            { println("OnNext $it") }
            , { println("OnError $it") }
            , { println("OnComplete") }
            , { println("OnSubscribe") }
        )

    println("\n\n\nretry（l:Long） 重试，即当出现错误时，让被观察者（Observable）重新发射数据 会导致死循环吧")
    Observable.create(object : ObservableOnSubscribe<Int> {
        override fun subscribe(emitter: ObservableEmitter<Int>) {
            emitter.onNext(1)
            emitter.onNext(2)
            emitter.onError(NullPointerException("我老捣乱"))
            emitter.onNext(3)
        }
    })//重试5次
        .retry(5).subscribe(observer)

    println("\n\n\n retry（Predicate predicate）  出现错误后，判断是否需要重新发送数据（若需要重新发送& 持续遇到错误，则持续重试）")
    Observable.create(object : ObservableOnSubscribe<Int> {
        override fun subscribe(emitter: ObservableEmitter<Int>) {
            emitter.onNext(1)
            emitter.onNext(2)
            emitter.onError(NullPointerException("我老捣乱"))
            emitter.onNext(3)
        }
    })
        .retry(Predicate {
            if (it.javaClass.equals(NullPointerException::class.java)) {
                println("空指针异常 停止")
                false
            } else {
                println("其他异常继续")
                true
            }
        })
        .subscribe(observer)

    println("\n\n\nretry( BiPredicate<Integer, Throwable>:Boolean) Int为重试过的次数 Boolean为是否继续重试")

    println("\n\n\nretryWhen（）遇到错误(onError)时，将发生的错误传递给一个新的被观察者（Observable），并决定是否需要重新订阅原始被观察者（Observable）& 发送事件")

    Observable.create(object : ObservableOnSubscribe<Int> {
        override fun subscribe(emitter: ObservableEmitter<Int>) {
            emitter.onNext(1)
            emitter.onNext(2)
            emitter.onNext(3 / 0)
            //emitter.onError(NullPointerException("我老捣乱"))

        }
    }).retryWhen(object : Function<Observable<Throwable>, Observable<Int>> {
        override fun apply(t: Observable<Throwable>): Observable<Int> {
            println("前面倒了")
            return t.flatMap(object : Function<Throwable, Observable<Int>> {
                override fun apply(t: Throwable): Observable<Int> {
                    //只有在Throwable是NullPointerException的情况下请求重订阅
                    if (ArithmeticException::class.java.equals(t::class.java)) {
                        println("是这个异常，重试")
                        return Observable.error(t)
                    }
                    println("停止")
                    return Observable.error(t)
                }
            })
        }
    }).subscribe(observer)

    println("\n\n\n 3.6 重复发送 repeat（） 无条件地、重复发送 被观察者事件")
    observable.repeat(3).subscribe(observer)

    println("\n\n\n 3.6 重复发送 repeatWhen（） 有条件地、重复发送 被观察者事件")
    observable.repeatWhen(object : Function<Observable<Any>, ObservableSource<Int>> {
        override fun apply(t: Observable<Any>): ObservableSource<Int> {
            return t.flatMap(object : Function<Any, ObservableSource<Int>> {
                override fun apply(t: Any): ObservableSource<Int> {
                    // Observable.empty() = 发送Complete事件，但不会回调观察者的onComplete（）
                    return Observable.empty()

                    // 返回Error事件 = 回调onError（）事件，并接收传过去的错误信息。
                    //return Observable.error( Throwable("不再重新订阅事件"))

                    // 情况2：若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                    //return Observable.just(1)
                }
            })
        }
    })
        .subscribe(observer)


}


