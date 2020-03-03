package com.iffy.module_view.dispatch.basic

import android.util.Log
import android.view.MotionEvent
import com.iffy.module_base.BaseActivity
import com.iffy.module_view.R

class DispatchActivity : BaseActivity() {

    val MTAG = this::class.java.simpleName


    override fun getContentId(): Int {
        return R.layout.activity_dispatch
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e(MTAG, "dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    /**
     * Activity只有分发 和 touchEvent 没有拦截功能
    * */

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e(MTAG, "onTouchEvent")
        return super.onTouchEvent(event)
    }

}