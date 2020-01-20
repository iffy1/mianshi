package com.iffy.module_animation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.iffy.module_animation.databinding.ActivityMainBinding
import com.iffy.module_animation.model.AnimationTypeModel
import com.iffy.module_animation.presenter.ClickEventPresenter
import com.iffy.module_animation.propertyAnimation.Interpolator.InterpolatorActivity
import com.iffy.module_animation.propertyAnimation.ObjectAnimatorActivity
import com.iffy.module_animation.propertyAnimation.ValueAnimatorActivity
import com.iffy.module_animation.sceneTransition.Aactivity
import com.iffy.module_animation.transition.SimpleTransitionDemoActivity
import com.iffy.module_base.BaseActivity


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        //数据绑定
        val animationType = AnimationTypeModel(
            getString(R.string.content) + "真的吗"
            ,getString(R.string.objectAnimator)
            ,getString(R.string.valueAnimator)
            , getString(R.string.valueAnimatorPolator)
            , getString(R.string.viewAnimation)
            , getString(R.string.sceneTransition)
        )

        //数据绑定文件名是根据 Activity_main的文件名确定的
        //例如你 以 event_act.xml 那么 文件名就是 [eventact]binding
        //[] 中就是你的 布局文件名

        /**  例如 EventActBinding eventactBinding=
        DataBindingUtil.setContentView(this,R.layout.event_act);*/
        val activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        //绑定view
        activityMainBinding.content = animationType

        //绑定监听事件
        activityMainBinding.clickEvent=ClickEventPresenter()


        //数据绑定
    }
//可以配合xml的onclick:""
//    fun gotoObjectAnimatorActivity(v: View) {
//        val intent = Intent()
//        intent.setClass(this, ObjectAnimatorActivity::class.java)
//        startActivity(intent)
//    }
//
//    fun gotoValueAnimatorActivity(v: View) {
//        val intent = Intent()
//        intent.setClass(this, ValueAnimatorActivity::class.java)
//        startActivity(intent)
//    }
//
//    fun gotoInterpolatorActivity(v: View) {
//        val intent = Intent()
//        intent.setClass(this, InterpolatorActivity::class.java)
//        startActivity(intent)
//    }
//
//    fun gotoViewAnimationActivity(v: View) {
//        val intent = Intent()
//        intent.setClass(this, SimpleTransitionDemoActivity::class.java)
//        startActivity(intent)
//    }
//
//    fun gotosceneAnimationActivity(v: View) {
//        val intent = Intent()
//        intent.setClass(this, Aactivity::class.java)
//        startActivity(intent)
//    }


}

