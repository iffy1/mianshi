package com.iffy.mianshi.rxjava.filter.demoB

import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R
import com.iffy.mianshi.rxjava.retrofitUtil.Constant
import com.iffy.mianshi.rxjava.retrofitUtil.translate_interface
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class InputSearchUnbounceActivity : AppCompatActivity() {
    lateinit var et: EditText
    lateinit var willsend: TextView
    lateinit var back: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_debounce)
        et = findViewById(R.id.search)
        willsend = findViewById(R.id.willsend)
        back = findViewById(R.id.back)


        var retrofit = Retrofit.Builder().baseUrl(Constant.BASEURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var service = retrofit.create(translate_interface::class.java)


        // RxTextView.afterTextChangeEvents(et)//返回event
        var a = RxTextView.textChanges(et)//返回charSequence
            .skip(1)//避免第一次空 调用服务器
            .debounce(1, TimeUnit.SECONDS)//延时去服务器查询翻译结果
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                willsend.text = it.toString()
                service.getChinese(et.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (!TextUtils.isEmpty(it.content?.out)) {
                            back.text = it.content?.out
                        } else if (!TextUtils.isEmpty(it.content?.word_mean.toString())) {
                            back.text = it.content?.word_mean.toString()
                        } else {
                            back.text = "服务器空调用"
                        }
                    }
            }
    }
}