package com.iffy.mianshi.lrucache

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect


import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RecycleViewDivider : RecyclerView.ItemDecoration {

    private var mPaint: Paint? = null
    private var mDivider: Drawable? = null
    private var mDividerHeight = 2//分割线高度，默认为1px
    private var mOrientation: Int =
        0//列表的方向：LinearLayoutManager.VERTICAL或LinearLayoutManager.HORIZONTAL

    /**
     * 默认分割线：高度为2px，颜色为灰色
     *
     * @param context
     * @param orientation 列表方向
     */
    constructor(context: Context, orientation: Int) {
        require(!(orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL)) { "请输入正确的参数！" }
        mOrientation = orientation

        val a = context.obtainStyledAttributes(ATTRS)
        mDivider = a.getDrawable(0)
        a.recycle()
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation 列表方向
     * @param drawableId  分割线图片
     */
    constructor(context: Context, orientation: Int, drawableId: Int) {
        //this(context, orientation);
        mDivider = ContextCompat.getDrawable(context, drawableId)
        mDividerHeight = mDivider!!.intrinsicHeight
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation   列表方向
     * @param dividerHeight 分割线高度
     * @param dividerColor  分割线颜色
     */
    constructor(context: Context, orientation: Int, dividerHeight: Int, dividerColor: Int) {
        //this(context, orientation);
        mDividerHeight = dividerHeight
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint!!.setColor(dividerColor)
        mPaint!!.setStyle(Paint.Style.FILL)
    }


    //获取分割线尺寸
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(0, 0, 0, mDividerHeight)
    }

    //绘制分割线
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    //绘制横向 item 分割线
    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        val left = parent.getPaddingLeft()
        val right = parent.getMeasuredWidth() - parent.getPaddingRight()
        val childSize = parent.getChildCount()
        for (i in 0 until childSize) {
            val child = parent.getChildAt(i)
            val layoutParams = child.getLayoutParams() as RecyclerView.LayoutParams
            val top = child.getBottom() + layoutParams.bottomMargin
            val bottom = top + mDividerHeight
            if (mDivider != null) {
                mDivider!!.setBounds(left, top, right, bottom)
                mDivider!!.draw(canvas)
            }
            if (mPaint != null) {
                canvas.drawRect(
                    left.toFloat(),
                    top.toFloat(),
                    right.toFloat(),
                    bottom.toFloat(),
                    mPaint!!
                )
            }
        }
    }

    //绘制纵向 item 分割线
    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        val top = parent.getPaddingTop()
        val bottom = parent.getMeasuredHeight() - parent.getPaddingBottom()
        val childSize = parent.getChildCount()
        for (i in 0 until childSize) {
            val child = parent.getChildAt(i)
            val layoutParams = child.getLayoutParams() as RecyclerView.LayoutParams
            val left = child.getRight() + layoutParams.rightMargin
            val right = left + mDividerHeight
            if (mDivider != null) {
                mDivider!!.setBounds(left, top, right, bottom)
                mDivider!!.draw(canvas)
            }
            if (mPaint != null) {
                canvas.drawRect(
                    left.toFloat(),
                    top.toFloat(),
                    right.toFloat(),
                    bottom.toFloat(),
                    mPaint!!
                )
            }
        }
    }

    companion object {
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }
}