package com.iffy.module_animation.sceneTransition

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.iffy.module_animation.R
import com.iffy.module_base.BaseActivity

class Aactivity : BaseActivity() {
    override fun getContentId(): Int {
       return  R.layout.activity_sence_transition
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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