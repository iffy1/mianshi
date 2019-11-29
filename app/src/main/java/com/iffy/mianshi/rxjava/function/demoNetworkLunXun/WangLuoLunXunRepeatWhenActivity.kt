package com.iffy.mianshi.rxjava.function.demoNetworkLunXun

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R
import com.iffy.mianshi.rxjava.retrofitUtil.Constant
import com.iffy.mianshi.rxjava.retrofitUtil.Translation
import com.iffy.mianshi.rxjava.retrofitUtil.translate_interface
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//我将结合 Retrofit 与RxJava 实现 有条件的 轮询 需求
//3.1 步骤说明
//
//    添加依赖
//    创建 接收服务器返回数据 的类
//    创建 用于描述网络请求 的接口（区别于Retrofit传统形式）
//    创建 Retrofit 实例
//    创建 网络请求接口实例 并 配置网络请求参数（区别于Retrofit传统形式）
//    发送网络请求（区别于Retrofit传统形式）
//    发送网络请求
//    对返回的数据进行处理
//
class WangLuoLunXunRepeatWhenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
        test()
    }
}

fun test() {
    var retryCount = 0

    val retrofit = Retrofit
        .Builder()
        .baseUrl(Constant.BASEURL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val servise = retrofit.create(translate_interface::class.java)

    val observable: Observable<Translation> = servise.getCall("happy")
    val observer = object : Observer<Translation> {
        override fun onComplete() {
            println("onComplete")
        }

        override fun onSubscribe(d: Disposable) {
            println("onSubscribe")
        }

        override fun onNext(t: Translation) {
            retryCount++
            println("onNext t out:${t.content?.out}")
            println("onNext t word_mean:${t.content?.word_mean}")
        }

        override fun onError(e: Throwable) {
            println("onError $e")
        }

    }

    //发送网络请求 & 通过repeatWhen（）进行轮询
    observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        // 在Function函数中，必须对输入的 Observable<Object>进行处理，此处使用flatMap操作符接收上游的数据
        .repeatWhen(object : Function<Observable<Any>, ObservableSource<Int>> {
            override fun apply(t: Observable<Any>): ObservableSource<Int> {
                println("applyA $t")
                return t.flatMap(object : Function<Any, ObservableSource<Int>> {
                    override fun apply(t: Any): ObservableSource<Int> {
                        println("applyB $t retryCount: $retryCount")
                        //重试次数
                        if (retryCount >= 3) {
                            //轮询超过3次 停止
                            return Observable.error(Throwable("aaa"))
                        } else {
                            //不到三次 继续轮询 just的参数没有意义 发什么都行
                            return Observable.just(1).delay(1000, TimeUnit.MICROSECONDS)
                        }


                    }
                })
            }
        }).subscribe(observer)


}