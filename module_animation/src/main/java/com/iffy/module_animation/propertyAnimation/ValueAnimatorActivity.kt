package com.iffy.module_animation.propertyAnimation

import android.animation.ValueAnimator
import android.os.Bundle
import android.widget.TextView
import com.iffy.module_animation.R
import com.iffy.module_base.BaseActivity


class ValueAnimatorActivity : BaseActivity() {
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