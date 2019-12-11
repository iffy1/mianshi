package com.iffy.mianshi.viewOptimiz


import android.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.annotation.Nullable

import java.util.ArrayList

/**
 * 1000个点，震荡图
 * Created by GreendaMi on 2017/5/8.
 */

class ShakeMaps : View {
    internal var mContext: Context
    internal var max: Int = 0
    internal var min: Int = 0
    //两种线的颜色
    internal var mColor1 = -0xea6b9f
    internal var mColor2 = -0x14d1d8
    internal var mPaint: Paint? = null
    internal var gap = 10.0f//点与点之间的间距

    internal var startX = 10.0f

    internal var borderTopAndBottom = 20.0f//上下留白
    internal var botderLeft = 10.0f//左边留白
    internal var botderLefttep = botderLeft
    internal var lastStartX = startX//抬起手指后，当前控件最左边X的坐标

    internal var mXDown: Int = 0
    internal var mLastX: Int = 0

    //最短滑动距离
    internal var a = 0.0f

    internal var mData: List<dataObj>? = ArrayList()

    fun setmData(mData: List<dataObj>, max: Int, min: Int) {
        this.mData = mData
        this.max = max
        this.min = min
        postInvalidate()
    }

    fun initPaint() {
        if (mPaint == null) {
            mPaint = Paint()
        } else {
            mPaint!!.reset()
        }
        mPaint!!.isAntiAlias = true
        //文字大小
        mPaint!!.textSize = (width / 32).toFloat()
    }

    constructor(context: Context) : super(context) {
        mContext = context
        a = DPUnitUtil.px2dip(context, ViewConfiguration.get(context).scaledDoubleTapSlop)
        isClickable = true
        initializeTheUnit()
        initPaint()
    }

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {
        mContext = context
        a = DPUnitUtil.px2dip(context, ViewConfiguration.get(context).scaledDoubleTapSlop)
        isClickable = true
        initializeTheUnit()
        initPaint()
    }

    //初单位
    fun initializeTheUnit() {
        gap = DPUnitUtil.dip2px(mContext, 5.0f)

        startX = DPUnitUtil.dip2px(mContext, 5.0f)

        borderTopAndBottom = DPUnitUtil.dip2px(mContext, 10.0f)
        botderLeft = DPUnitUtil.dip2px(mContext, 10.0f)
        botderLefttep = botderLeft
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //画背景
        drawTheBackground(canvas)
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        //画y轴
        drawTheY(canvas)
        //画虚线，关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        //画x轴横线
        drawTheX(canvas)
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        //画数据

        drawDatas(canvas)
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)

    }

    private fun drawTheY(canvas: Canvas) {
        System.out.println("drawTheY")
        initPaint()
        mPaint!!.color = -0x6d253c
        mPaint!!.strokeWidth = DPUnitUtil.dip2px(mContext, 2.0f)
        //留出文字距离
        botderLeft = botderLefttep + (mPaint!!.measureText(min.toString() + "") * 1.2f).toInt()
        //画出纵坐标线
        canvas.drawLine(
            botderLeft.toFloat(),
            borderTopAndBottom.toFloat(),
            botderLeft.toFloat(),
            (height - borderTopAndBottom).toFloat(),
            mPaint!!
        )
    }

    private fun drawDatas(canvas: Canvas) {
        if (mData == null || mData!!.size == 0) {
            return
        }
        initPaint()
        mPaint!!.color = mColor1
        mPaint!!.strokeWidth = DPUnitUtil.dip2px(mContext, 1.0f)
        //画y1的线
        for (i in 0 until mData!!.size - 1) {
            //超过屏幕范围，不再绘制
            if (startX + botderLeft + gap * i < botderLeft) {
                continue
            }
            if (startX + botderLeft + gap * (i + 1) > width) {
                break
            }

            canvas.drawLine(
                (startX + botderLeft + gap * i).toFloat(),
                getHByValue(mData!![i].y1),
                (startX + botderLeft + gap * (i + 1)).toFloat(),
                getHByValue(
                    mData!![i + 1].y1
                ),
                mPaint!!
            )

            //画开始小球和结束小球
            if (i == 0) {
                canvas.drawCircle((startX + botderLeft + gap * i).toFloat(), getHByValue(mData!![i].y1), 8f, mPaint!!)
                mPaint!!.color = -0x1
                canvas.drawCircle((startX + botderLeft + gap * i).toFloat(), getHByValue(mData!![i].y1), 4f, mPaint!!)
                mPaint!!.color = mColor1
            }
            if (i == mData!!.size - 2) {
                canvas.drawCircle(
                    (startX + botderLeft + gap * (i + 1)).toFloat(),
                    getHByValue(mData!![i + 1].y1),
                    8f,
                    mPaint!!
                )
                mPaint!!.color = -0x1
                canvas.drawCircle(
                    (startX + botderLeft + gap * (i + 1)).toFloat(),
                    getHByValue(mData!![i + 1].y1),
                    4f,
                    mPaint!!
                )
                mPaint!!.color = mColor1
            }
        }

        //画y2的线
        initPaint()
        mPaint!!.color = mColor2
        mPaint!!.strokeWidth = DPUnitUtil.dip2px(mContext, 1.0f)
        for (i in 0 until mData!!.size - 1) {
            //超过屏幕范围，不再绘制
            if (startX + botderLeft + gap * i < botderLeft) {
                continue
            }
            if (startX + botderLeft + gap * (i + 1) > width) {
                break
            }
            canvas.drawLine(
                (startX + botderLeft + gap * i).toFloat(),
                getHByValue(mData!![i].y2),
                (startX + botderLeft + gap * (i + 1)).toFloat(),
                getHByValue(
                    mData!![i + 1].y2
                ),
                mPaint!!
            )

            //画开始小球和结束小球
            if (i == 0) {
                canvas.drawCircle((startX + botderLeft + gap * i).toFloat(), getHByValue(mData!![i].y2), 8f, mPaint!!)
                mPaint!!.color = -0x1
                canvas.drawCircle((startX + botderLeft + gap * i).toFloat(), getHByValue(mData!![i].y2), 4f, mPaint!!)
                mPaint!!.color = mColor2
            }
            if (i == mData!!.size - 2) {
                canvas.drawCircle(
                    (startX + botderLeft + gap * (i + 1)).toFloat(),
                    getHByValue(mData!![i + 1].y2),
                    8f,
                    mPaint!!
                )
                mPaint!!.color = -0x1
                canvas.drawCircle(
                    (startX + botderLeft + gap * (i + 1)).toFloat(),
                    getHByValue(mData!![i + 1].y2),
                    4f,
                    mPaint!!
                )
                mPaint!!.color = mColor2
            }
        }

    }


    private fun drawTheX(canvas: Canvas) {
        //画中间的线
        initPaint()
        //纵坐标文字距离Y轴线的距离
        val textLeftBorder = DPUnitUtil.dip2px(mContext, 2.0f)
        mPaint!!.color = -0x6d253c
        //0度线
        mPaint!!.strokeWidth = DPUnitUtil.dip2px(mContext, 1.0f)
        canvas.drawLine(
            (botderLeft - textLeftBorder).toFloat(),
            (height / 2).toFloat(),
            width.toFloat(),
            (height / 2).toFloat(),
            mPaint!!
        )
        mPaint!!.color = mContext.resources.getColor(R.color.black,null)
        //每条横线的上下间隔
        val step = (height / 2 - borderTopAndBottom) / 5

        val stepInt = (max - min) / 10
        //纵坐标文字大小

        mPaint!!.strokeWidth = DPUnitUtil.dip2px(mContext, 1.0f)
        mPaint!!.pathEffect = DashPathEffect(floatArrayOf(15f, 15f), 0f)
        for (i in 0..10) {
            //写纵坐标文字
            mPaint!!.color = -0xea6b9f
            canvas.drawText(
                (max - i * stepInt).toString() + "",
                botderLeft - mPaint!!.measureText((max - i * stepInt).toString() + "") - textLeftBorder,
                borderTopAndBottom + i * step + (mPaint!!.fontMetrics.bottom - mPaint!!.fontMetrics.top) / 2 - mPaint!!.fontMetrics.bottom,
                mPaint!!
            )
            if (i == 5) {
                continue
            }
            mPaint!!.color = -0x226d253c
            mPaint!!.strokeWidth = DPUnitUtil.dip2px(mContext, 0.5f)
            canvas.drawLine(
                botderLeft.toFloat(),
                borderTopAndBottom + i * step,
                width.toFloat(),
                borderTopAndBottom + i * step,
                mPaint!!
            )
        }
    }

    private fun drawTheBackground(canvas: Canvas) {}

    //触摸处理
    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (mData == null || mData!!.size == 0) {
            return super.onTouchEvent(event)
        }
        val action = event.action

        when (action) {
            MotionEvent.ACTION_DOWN ->
                // 按下
                mXDown = event.rawX.toInt()

            MotionEvent.ACTION_MOVE -> {
                // 移动
                mLastX = event.rawX.toInt()

                //1.5是加速滑动
                var tempx:Float = (lastStartX + (mLastX - mXDown) * 1.5).toFloat()
                //                if (Math.abs(lastStartX - mXDown) < a) {
                //                    break;
                //                }
                //滑动限制
                if (tempx > botderLefttep) {
                    tempx = botderLefttep
                }
                if (tempx < -((mData!!.size + 1) * gap + botderLeft - width)) {
                    tempx = -((mData!!.size + 1) * gap + botderLeft - width)
                }
                if (startX == tempx) {
                    //说明已经绘制过，不再绘制
                    //break
                }
                //1.5是加速滑动
                startX = tempx
                postInvalidate()
            }

            MotionEvent.ACTION_UP -> {
                // 抬起
                lastStartX = startX
                postInvalidate()
            }
            else -> {
            }
        }
        return super.onTouchEvent(event)
    }

    //通过Y的值获取Y轴坐标
    private fun getHByValue(y: Int): Float {
        return (max - y).toFloat() / (max - min).toFloat() * (height - borderTopAndBottom * 2f) + borderTopAndBottom
    }

    class dataObj {
        internal var x: Int = 0
        internal var y1: Int = 0
        internal var y2: Int = 0

        fun setX(x: Int) {
            this.x = x
        }

        fun setY1(y1: Int) {
            this.y1 = y1
        }

        fun setY2(y2: Int) {
            this.y2 = y2
        }
    }
}