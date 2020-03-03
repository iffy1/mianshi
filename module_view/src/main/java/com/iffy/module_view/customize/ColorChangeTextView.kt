package com.iffy.module_view.customize


import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build

import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import androidx.annotation.RequiresApi
import com.iffy.module_view.R


@RequiresApi(Build.VERSION_CODES.M)
class ColorChangeTextView
//在有默认参数值的方法中使用@JvmOverloads注解，则Kotlin就会暴露多个重载方法。
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {
    companion object {
        const val TAG = "ColorChangeTextView"
    }

    var mWenzi: String = ""
    var mTextSize = 10f
    var mTextColor = context.getColor(R.color.colorAccent)
    var mTextPaint = Paint()

    init {
        //获取xml中的属性
        val obtainStyledAttributes =
            context.obtainStyledAttributes(attrs, R.styleable.ColorChangeTextView)
        mWenzi = "${obtainStyledAttributes.getString(R.styleable.ColorChangeTextView_wenzi)}"
        mTextSize =
            obtainStyledAttributes.getFloat(R.styleable.ColorChangeTextView_daxiao, mTextSize)
        mTextColor =
            obtainStyledAttributes.getColor(R.styleable.ColorChangeTextView_yanse, mTextColor)
        //没用的变量及时释放
        obtainStyledAttributes.recycle()

        //初始化画笔 pixel
        mTextPaint.textSize = mTextSize
        mTextPaint.color = mTextColor

        Log.e(TAG, "mWenzi: $mWenzi")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var resultWidth = 0
        var resultHeight = 0
        //獲取父類測量模式
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        val parentHight = MeasureSpec.getSize(heightMeasureSpec)

        //AT_MOST->wrap_content
        //EXACTLY->dp/Match_parent
        when (widthMode) {
            //如果mode是 wrap_content 会走AT_MOST
            MeasureSpec.AT_MOST -> {
                Log.e(TAG, "父容器要求")
                val bounds = Rect()
                mTextPaint.getTextBounds(mWenzi, 0, mWenzi.length, bounds)
                resultWidth = paddingLeft + paddingRight + bounds.width()
            }
            //如果父类宽度是 match_parent或者具体数值> EXACTLY
            MeasureSpec.EXACTLY -> {
                Log.e(TAG, "父容器对子view的要求是EXACTLY 就直接用父容器的值")
                resultWidth = parentWidth
            }

            //如果父类宽度是 match_parent或者具体数值> UNSPECIFIED
            MeasureSpec.UNSPECIFIED -> {
            }

        }

        when (heightMode) {
            //如果mode是 wrap_content 会走AT_MOST
            MeasureSpec.AT_MOST -> {
                Log.e(TAG, "宽度 父类 match_parent或者具体数值 走 EXACTLY")
                val bounds = Rect()
                mTextPaint.getTextBounds(mWenzi, 0, mWenzi.length, bounds)
                resultHeight = paddingTop + paddingBottom + bounds.height()
            }
            //如果父类宽度是 match_parent或者具体数值> EXACTLY
            MeasureSpec.EXACTLY -> {
                Log.e(TAG, "宽度 父类 match_parent或者具体数值 走 EXACTLY")
                resultHeight = parentHight
            }

            //如果父类宽度是 match_parent或者具体数值> UNSPECIFIED
            MeasureSpec.UNSPECIFIED -> {
            }

        }
        setMeasuredDimension(resultWidth, resultHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            // save()
            //(x,y)左下开始绘制
            Log.e(TAG, "paddingLeft:" + paddingLeft)
            Log.e(TAG, "paddingRight:" + paddingRight)
            val fontMetrics = mTextPaint.getFontMetrics()
            Log.e(TAG, "fontMetrics.top:" + fontMetrics.top)
            Log.e(TAG, "fontMetrics.bottom:" + fontMetrics.bottom)
            Log.e(TAG, "fontMetrics.ascent:" + fontMetrics.ascent)
            Log.e(TAG, "fontMetrics.leading:" + fontMetrics.leading)
            Log.e(TAG, "fontMetrics.descent:" + fontMetrics.descent)
            Log.e(TAG, "height:" + height)

            val y =
                height +fontMetrics.ascent
            drawText(mWenzi, 0f + paddingLeft, y - paddingTop, mTextPaint)

            //restore()
        }


    }


}