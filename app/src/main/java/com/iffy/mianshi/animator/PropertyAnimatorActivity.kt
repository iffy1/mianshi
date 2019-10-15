package com.iffy.mianshi.animator

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R

class PropertyAnimatorActivity : AppCompatActivity() {
    lateinit var tv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_anima)
        tv = findViewById(R.id.tv)

        var oba = ObjectAnimator.ofInt(tv,"width",1,4000)
//        var aset = AnimatorSet()
//        aset.play(oba)
//        aset.duration = 2000
//        aset.start()
        oba.duration = 2000
        oba.start()

    }
}