package com.iffy.mianshi.rxjava.create.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R
import com.iffy.mianshi.rxjava.retrofitUtil.Constant
import com.iffy.mianshi.rxjava.retrofitUtil.translate_interface
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RxjavaCreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        val retrofit = Retrofit.Builder().baseUrl(Constant.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient().build())//添加log工具
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        val service = retrofit.create(translate_interface::class.java)

        var data = arrayOf("en", "ja", "de", "zh", "ko", "es", "fr")
        //使用星号用来展开数组
        Observable.fromArray(*data)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                //println("onNextA $it")
                val Observable_translated = service.getCallC(it.toString())
                Observable_translated.subscribe {
                    //println("OnNext ${it.content}")
                    it.show()
                }

            }

        //轮询方法
        Observable.interval(1, TimeUnit.SECONDS).subscribe {
            val retrofit = Retrofit.Builder().baseUrl(Constant.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient().build())//添加log工具
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
            val service = retrofit.create(translate_interface::class.java)
            var obvb = service.getCallC(data[it.toInt() % 6])
            obvb.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe{
                it.show()
            }

        }


    }

    //替换为打开log的okhttp客户端
    fun getClient(): OkHttpClient.Builder {
        var httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(15, TimeUnit.SECONDS)
        //add log record

        //打印网络请求日志
        var httpLoggingInterceptor = LoggingInterceptor.Builder()
            .loggable(true)
            .setLevel(Level.BASIC)
            .log(Platform.INFO)
            .request("请求")
            .response("响应")
            .build()
        httpClientBuilder.addInterceptor(httpLoggingInterceptor)

        return httpClientBuilder
    }

}