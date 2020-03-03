package com.iffy.module_view.customize

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class CustomizeFlowViewGoup
@JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ViewGroup(context, attrs, defStyleAttr, defStyleRes) {
    //每一行的views
    lateinit var lineViews: ArrayList<ArrayList<View>>
    //每一行的高度
    lateinit var lineHeights: ArrayList<Int>

    fun initRecord() {
        //每一行的view
        lineViews = ArrayList()
        //每一行的高度
        lineHeights = ArrayList()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        initRecord()
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var mWidth = MeasureSpec.getSize(widthMeasureSpec)
        var mHeight = 0 //使用wrapcontent模式
        var line = 0
        var lineWidth = 0
        lineHeights.add(0)
        lineViews.add(ArrayList())//增加第一行的空队列
        //遍历子view
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            //获取子view的measureSpec view里最难的一个方法
            //需要传入的参数为（父容器的Spec,
            // 父容器的padding(padding是父容器的私房钱不能给子view用)，子view param里面定义的的宽度）
            val childWidthMeasureSpec = getChildMeasureSpec(
                widthMeasureSpec,
                paddingLeft + paddingRight,
                childView.layoutParams.width
            )
            val childHeightMeasureSpec = getChildMeasureSpec(
                heightMeasureSpec,
                paddingTop + paddingBottom,
                childView.layoutParams.height
            )
            //调用子view的onMeasure让子view测量自己的尺寸
            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec)
            //调用measure 之后measuredWidth和measuredHeight就可以使用了
            //但是  childView.width childView.height 这两个值要在onLayout后才能有值这里不要用错

            //子view加上自己的padding值
            val childFullWidth =
                childView.paddingLeft + childView.paddingRight + childView.measuredWidth
            val childFullHeight =
                childView.measuredHeight + childView.paddingTop + childView.paddingBottom
            //如果行的宽度不能放下最后一个view 换行
            if (lineWidth + childFullWidth > mWidth) {
                line++
                lineWidth = childFullWidth//第一个子view的宽度
                lineViews.add(ArrayList())//增加一行views
                lineHeights.add(childFullHeight)//增加一行高度记录
            } else {
                //行宽加上这个子view的宽度
                lineWidth += childFullWidth
                //最高的view作为这一行最高的高度
                if (childFullHeight > lineHeights[line]) {
                    lineHeights[line] = childFullHeight
                }
            }
            lineViews[line].add(childView)//每行加入子view
        }
        //算出总高度
        lineHeights.forEach {
            mHeight += it
        }

        //如果已经给定宽度了（EXACTLY）就用给定的尺寸
        var widthMode = MeasureSpec.getMode(widthMeasureSpec)
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = MeasureSpec.getSize(widthMeasureSpec)
        }
        //如果已经给定高度了（EXACTLY）就用给定的尺寸
        var heightMode = MeasureSpec.getMode(heightMeasureSpec)
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = MeasureSpec.getSize(heightMeasureSpec)
        }
        //重要方法 设置自己的宽高
        setMeasuredDimension(mWidth, mHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var i = 0
        var y = 0
        lineViews.forEach {
            var x = 0
            it.forEach { view ->
                view.layout(
                    x + view.paddingLeft,
                    y + view.paddingTop,
                    x + view.measuredWidth + view.paddingRight,
                    y + view.measuredHeight + view.paddingBottom
                )
                x += view.measuredWidth + view.paddingLeft + view.paddingRight
            }
            x = 0
            y += lineHeights[i]
            i++
        }
    }


}