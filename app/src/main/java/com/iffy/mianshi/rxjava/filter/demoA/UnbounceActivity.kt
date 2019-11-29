package com.iffy.mianshi.rxjava.filter.demoA

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit

class UnbounceActivity : AppCompatActivity() {
    lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unbunce)

        btn = findViewById<Button>(R.id.btn_unbunce)

        //发送数据事件时，若发送事件的间隔小于debounce 指定时间，就会丢弃前一次的数据，时间大于debounce 指定时间才会发送
        //所以不能用debounce做按钮防抖
        /** var a =RxView.clicks(btn)
        .debounce(2, TimeUnit.SECONDS)
        .subscribe { println("他点我了") }*/

        //此处采用了RxBinding：RxView.clicks(button) = 对控件点击进行监听，需要引入依赖：compile 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
        //但由于使用了throttleFirst（）操作符，所以只会发送该段时间内的第1次点击事件
        var a = RxView.clicks(btn)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe {
                println("他点我了")
            }
    }

}