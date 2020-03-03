package com.iffy.module_view.dispatch.basic

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout

class MyDispatchViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int=0,
    defStyleRes: Int=0
) :
    FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    val TAG = this::class.java.simpleName

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e(TAG, "dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    //是不是拦截
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.e(TAG, "onInterceptTouchEvent")
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e(TAG, "onTouchEvent")
        return super.onTouchEvent(event)
    }

}