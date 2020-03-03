package com.iffy.module_view.dispatch.basic

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class MyDispatchView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int =0,
    defStyleRes: Int=0
) :
    View(context, attrs, defStyleAttr, defStyleRes) {

    val TAG = "MyDispatchView"

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.e(TAG, "dispatchTouchEvent")
        return super.dispatchTouchEvent(event)
    }

    /**
     * View没有拦截操作
     * */

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e(TAG, "onTouchEvent")
        return super.onTouchEvent(event)
    }
}