package com.iffy.module_animation.presenter

import android.content.Intent
import android.util.Log
import android.view.View
import com.iffy.module_animation.propertyAnimation.Interpolator.InterpolatorActivity
import com.iffy.module_animation.propertyAnimation.ObjectAnimatorActivity
import com.iffy.module_animation.propertyAnimation.ValueAnimatorActivity
import com.iffy.module_animation.sceneTransition.Aactivity
import com.iffy.module_animation.transition.SimpleTransitionDemoActivity

//点击事件绑定
//对应XML里面的android:onClick="@{clickEvent::gotoValueAnimatorActivity}"
class ClickEventPresenter {
    val TAG = this.javaClass.name
    fun gotoObjectAnimatorActivity(v: View) {
        Log.d(TAG,"gotoObjectAnimatorActivity")
        val intent = Intent()
        intent.setClass(v.context, ObjectAnimatorActivity::class.java)
        v.context.startActivity(intent)
    }

    fun gotoValueAnimatorActivity(v: View) {
        Log.d(TAG,"gotoValueAnimatorActivity")
        val intent = Intent()
        intent.setClass(v.context, ValueAnimatorActivity::class.java)
        v.context.startActivity(intent)
    }

    fun gotoInterpolatorActivity(v: View) {
        Log.d(TAG,"gotoInterpolatorActivity")
        val intent = Intent()
        intent.setClass(v.context, InterpolatorActivity::class.java)
        v.context.startActivity(intent)
    }

    fun gotoViewAnimationActivity(v: View) {
        Log.d(TAG,"gotoViewAnimationActivity")
        val intent = Intent()
        intent.setClass(v.context, SimpleTransitionDemoActivity::class.java)
        v.context.startActivity(intent)
    }

    fun gotosceneAnimationActivity(v: View) {
        Log.d(TAG,"gotosceneAnimationActivity")
        val intent = Intent()
        intent.setClass(v.context, Aactivity::class.java)
        v.context.startActivity(intent)
    }
}