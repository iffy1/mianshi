package com.iffy.mianshi.rxjava.function.wangluoChuCuoChongShi

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
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

//1打开飞行模式
//打开app,并且在重试第10次之前 关闭飞行模式，否则超过10次不再重试

class WangLuoChuCuoChongShiActivity : AppCompatActivity() {
    var retryCount = 0
    private val MAX_RETRY = 10
    lateinit var observable: Observable<Translation>
    lateinit var observer: Observer<Translation>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val service = retrofit.create(translate_interface::class.java)
        observable = service.getCall("say")

        observer = object : Observer<Translation> {
            override fun onComplete() {
                println("onComplete()")
            }

            override fun onSubscribe(d: Disposable) {
                println("onSubscribe")
            }

            override fun onNext(t: Translation) {
                println("onNext() t out:${t.content?.out}")
                println("onNext() t word_mean:${t.content?.word_mean}")
            }

            override fun onError(e: Throwable) {
                println("onError() $e")
            }

        }

        //retrywhen()
        retry()
    }

    fun retry(){
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry(Predicate {
                /**
                 * 需求1：根据异常类型选择是否重试
                 * 即，当发生的异常 = 网络异常 = UnknownHostException异常 才选择重试
                 */

                /**
                 * 需求2：限制重试次数
                 * 即，当已重试次数 < 设置的重试次数，才选择重试
                 */
                //使用飞行模式来验证这个超次数
                //设置重试次数避免无限重试
                if (it::class.java.equals(UnknownHostException::class.java)) {
                    retryCount++
                    if (retryCount <= MAX_RETRY) {
                        println("网络异常 重试 $retryCount 次")
                        //每次重试的时间延长
                        /**
                         * 需求2：实现重试
                         * 通过返回的Observable发送的事件 = Next事件，从而使得retryWhen（）重订阅，最终实现重试功能
                         *
                         * 需求3：延迟1段时间再重试
                         * 采用delay操作符 = 延迟一段时间发送，以实现重试间隔设置
                         *
                         * 需求4：遇到的异常越多，时间越长
                         * 在delay操作符的等待时间内设置 = 每重试1次，增多延迟重试时间1s
                         */
                        Thread.sleep(retryCount*1000L)
                        true
                    } else {
                        retryCount = 0
                        println("重试次数超过限制 停止重试")
                        false
                    }
                } else {
                    println("发生了 非unknow host异常")
                    false
                }
            }).subscribe(observer)
    }

    private fun retrywhen() {
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retryWhen(object : Function<Observable<Throwable>, ObservableSource<String>> {
                override fun apply(t: Observable<Throwable>): ObservableSource<String> {
                    return t.flatMap(object : Function<Throwable, ObservableSource<String>> {
                        override fun apply(t: Throwable): ObservableSource<String> {
                            println(t)
                            /**
                             * 需求1：根据异常类型选择是否重试
                             * 即，当发生的异常 = 网络异常 = UnknownHostException异常 才选择重试
                             */

                            /**
                             * 需求2：限制重试次数
                             * 即，当已重试次数 < 设置的重试次数，才选择重试
                             */
                            //使用飞行模式来验证这个超次数
                            //设置重试次数避免无限重试
                            if (t::class.java.equals(UnknownHostException::class.java)) {
                                retryCount++
                                if (retryCount <= MAX_RETRY) {
                                    println("网络异常 重试 $retryCount 次")
                                    //每次重试的时间延长
                                    /**
                                     * 需求2：实现重试
                                     * 通过返回的Observable发送的事件 = Next事件，从而使得retryWhen（）重订阅，最终实现重试功能
                                     *
                                     * 需求3：延迟1段时间再重试
                                     * 采用delay操作符 = 延迟一段时间发送，以实现重试间隔设置
                                     *
                                     * 需求4：遇到的异常越多，时间越长
                                     * 在delay操作符的等待时间内设置 = 每重试1次，增多延迟重试时间1s
                                     */
                                    return Observable.just("a")
                                        .delay(retryCount * 1L, TimeUnit.SECONDS)
                                } else {
                                    retryCount = 0
                                    println("重试次数超过限制 停止重试")
                                    return Observable.error(Throwable("重试次数超过限制 停止重试"))
                                }
                            } else {
                                return Observable.error(Throwable("发生了 非unknow host异常"))
                            }
                        }
                    })

                }
            }).subscribe(observer)
    }


}