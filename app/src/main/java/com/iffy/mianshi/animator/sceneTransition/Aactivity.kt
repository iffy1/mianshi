package com.iffy.mianshi.animator.sceneTransition

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R

class Aactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sence_transition)
        setupWindowAnimations()

        var btn = findViewById<Button>(R.id.btn_zhuangchang)
        btn.setOnClickListener {
            var i = Intent()
            i.setClass(this, Bactivity::class.java)
            startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }

    }

    fun setupWindowAnimations() {
        // inflate from xml
//        var slide = TransitionInflater.from(this).inflateTransition(R.transition.activity_slide)
        // or create directly
        var slide = Slide()
        slide.setDuration(1000)
        slide.setSlideEdge(Gravity.LEFT)
        //getWindow().setEnterTransition(slide)
       // getWindow().setExitTransition(slide)
        getWindow().setReenterTransition(slide)
    }

}