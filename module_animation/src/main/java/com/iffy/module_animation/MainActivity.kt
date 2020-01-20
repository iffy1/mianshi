package com.iffy.module_animation

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.iffy.module_animation.propertyAnimation.Interpolator.InterpolatorActivity
import com.iffy.module_animation.propertyAnimation.ObjectAnimatorActivity
import com.iffy.module_animation.propertyAnimation.ValueAnimatorActivity
import com.iffy.module_animation.sceneTransition.Aactivity
import com.iffy.module_animation.transition.SimpleTransitionDemoActivity
import com.iffy.module_base.BaseActivity


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun gotoObjectAnimatorActivity(v: View) {
        val intent = Intent()
        intent.setClass(this, ObjectAnimatorActivity::class.java)
        startActivity(intent)
    }

    fun gotoValueAnimatorActivity(v: View) {
        val intent = Intent()
        intent.setClass(this, ValueAnimatorActivity::class.java)
        startActivity(intent)
    }

    fun gotoInterpolatorActivity(v: View) {
        val intent = Intent()
        intent.setClass(this, InterpolatorActivity::class.java)
        startActivity(intent)
    }

    fun gotoViewAnimationActivity(v: View) {
        val intent = Intent()
        intent.setClass(this, SimpleTransitionDemoActivity::class.java)
        startActivity(intent)
    }

    fun gotosceneAnimationActivity(v: View) {
        val intent = Intent()
        intent.setClass(this, Aactivity::class.java)
        startActivity(intent)
    }


}

