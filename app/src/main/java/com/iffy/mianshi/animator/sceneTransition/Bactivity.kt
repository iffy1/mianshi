package com.iffy.mianshi.animator.sceneTransition

import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R

class Bactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sence_transition_b)
        setupWindowAnimations()
    }

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