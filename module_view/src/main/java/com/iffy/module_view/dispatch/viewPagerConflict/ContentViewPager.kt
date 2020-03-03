package com.iffy.module_view.dispatch.viewPagerConflict

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

//自适应高度
class ContentViewPager(context: Context, attrs: AttributeSet?) :
    ViewPager(context, attrs) {

    //如果广告页滑动到头onInterceptTouchEvent会返回true 然后调用自己的onTouchEvent
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        var result = super.onInterceptTouchEvent(ev)
        Log.e("ContentViewPager","onInterceptTouchEvent $result")
        return result
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("ContentViewPager","dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("ContentViewPager","onTouchEvent")
        return super.onTouchEvent(ev)
    }
}