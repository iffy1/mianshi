package com.iffy.module_animation.sceneTransition

import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.iffy.module_animation.R
import com.iffy.module_base.BaseActivity

class Bactivity : BaseActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sence_transition_b)
        setupWindowAnimations()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setupWindowAnimations() {
        // inflate from xml
//        var slide = TransitionInflater.from(this).inflateTransition(R.transition.activity_slide)
        // or create directly
        var slide = Slide()
        slide.setDuration(1000)
        slide.setSlideEdge(Gravity.RIGHT)
        getWindow().setEnterTransition(slide)
        getWindow().setExitTransition(slide)

        var slideb = Slide()
        slideb.setDuration(2000)
        slideb.setSlideEdge(Gravity.TOP)
        getWindow().setReturnTransition(slideb)
        //getWindow().setReenterTransition(slide)
    }
}