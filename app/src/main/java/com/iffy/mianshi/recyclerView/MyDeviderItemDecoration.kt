package com.iffy.mianshi.recyclerView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.iffy.mianshi.R


class MyDeviderItemDecoration : RecyclerView.ItemDecoration {
    lateinit var mPaint: Paint
    private var mDividerHeight = 5//分割线高度，默认为1px
    private var mOrientation = RecyclerView.VERTICAL
    lateinit var mDeviderDrawable: Drawable
    var ATTRS = intArrayOf(android.R.attr.listDivider)//使用系统自带的listDivider


    constructor(context: Context, origination: Int) {
        this.mOrientation = origination
    }

    //Drawable分割线
    constructor(context: Context, origination: Int, mDrawableId: Int) : this(context, origination) {
        this.mDeviderDrawable = context.resources.getDrawable(mDrawableId, null)
        mDividerHeight = mDeviderDrawable.intrinsicHeight
    }

    //条形分割线 自定义颜色和高度
    constructor(context: Context, origination: Int, deviderHeight: Int, deviderColor: Int) : this(
        context,
        origination
    ) {
        this.mDividerHeight = deviderHeight
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.setColor(context.resources.getColor(deviderColor, null))
        mPaint.setStyle(Paint.Style.FILL)

    }

    //垂直滑动 绘制横向 item 分割线
    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft//获取分割线的左边距，即RecyclerView的padding值
        val right = parent.measuredWidth - parent.paddingRight//分割线右边距
        val childSize = parent.childCount
        //遍历所有item view，为它们的下方绘制分割线
        for (i in 0 until childSize) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + layoutParams.bottomMargin
            val bottom = top + mDividerHeight
            println("mDividerHeight:$mDividerHeight")
            if (::mDeviderDrawable.isInitialized) {
                println("$left, $top, $right, $bottom")
                mDeviderDrawable.setBounds(left, top+10, right, bottom+10)
                mDeviderDrawable.draw(canvas)
            }
            if (::mPaint.isInitialized) {
                canvas.drawRect(left.toFloat(), top.toFloat()+5, right.toFloat(), bottom.toFloat()+5, mPaint)
            }
        }
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.measuredHeight - parent.paddingBottom
        val childSize = parent.childCount
        for (i in 0 until childSize) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + layoutParams.rightMargin
            val right = left + mDividerHeight
            if (::mDeviderDrawable.isInitialized) {
                mDeviderDrawable.setBounds(left, top, right, bottom)
                mDeviderDrawable.draw(canvas)
            }
            if (::mPaint.isInitialized) {
                canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        when (mOrientation) {
            RecyclerView.VERTICAL -> drawHorizontal(canvas, parent)
            RecyclerView.HORIZONTAL -> drawVertical(canvas, parent)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        //设置偏移的高度是mDivider.getIntrinsicHeight，该高度正是分割线的高度
        if (mOrientation == RecyclerView.VERTICAL) {
            outRect.set(0, 0, 0, mDividerHeight)
        } else {
            outRect.set(0, 0, mDividerHeight, 0)
        }

    }
}