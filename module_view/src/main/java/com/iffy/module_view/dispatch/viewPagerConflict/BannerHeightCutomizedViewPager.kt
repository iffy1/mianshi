package com.iffy.module_view.dispatch.viewPagerConflict

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

//自适应高度
class BannerHeightCutomizedViewPager(context: Context, attrs: AttributeSet?) :
    ViewPager(context, attrs) {
    //重写onMeasure使viewpager能自适应高度
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var maxHeight = 0
        for (i in 0 until childCount) {
            //测量子view的高度
            getChildAt(i).measure(
                widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            )
            if (maxHeight < getChildAt(i).measuredHeight) {
                maxHeight = getChildAt(i).measuredHeight
            }
        }
        //从新制作viewpagerd的HeightMeasureSpec
        super.onMeasure(
            widthMeasureSpec,
            MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.EXACTLY)
        )
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        //不让父类拦截 滑动到头也不给父view
        parent.requestDisallowInterceptTouchEvent(true)

        val result = super.onTouchEvent(ev)
        Log.e("BannerHeightCutomizedV","onTouchEvent $result")
        return result
    }
}