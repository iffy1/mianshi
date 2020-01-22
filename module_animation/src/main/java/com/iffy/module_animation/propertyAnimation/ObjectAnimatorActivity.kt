package com.iffy.module_animation.propertyAnimation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.iffy.module_animation.R
import com.iffy.module_base.BaseActivity

class ObjectAnimatorActivity : BaseActivity() {
    override fun getContentId(): Int {
        return R.layout.activity_value_anima
    }

    lateinit var tv: TextView
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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