package com.iffy.mianshi.animator

import android.animation.ValueAnimator
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R

class ValueAnimatorActivity : AppCompatActivity() {
    lateinit var tv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_anima)
        tv = findViewById(R.id.tv)

        var va = ValueAnimator()
        va.setIntValues(10,20,30,40,50,60,70,80,90,100,200,300)
        va.setDuration(2000)
        va.interpolator

        va.addUpdateListener {

            println("我动了啊 ${it.animatedValue}")
            tv.height = it.animatedValue as Int
            tv.width = it.animatedValue as Int

        }
        va.start()

    }
}