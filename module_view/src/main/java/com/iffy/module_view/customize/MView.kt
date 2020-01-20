package com.iffy.module_view.customize

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.view.View
import com.iffy.module_view.R


class MView(var c: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    View(c, attrs, defStyleAttr) {
    constructor(c: Context, attrs: AttributeSet) : this(c, attrs, 0)
    constructor(c: Context) : this(c, null, 0)

    lateinit var mTextPain: Paint
    lateinit var mBackgroudPaint: Paint
    lateinit var rect:Rect
    lateinit var text:String

    init {
        initPaint()
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun initPaint() {
        mTextPain = Paint()
        mTextPain.setColor(c.getColor(R.color.green))

        mBackgroudPaint = Paint()
        mBackgroudPaint.setColor(c.getColor(R.color.colorAccent))
        rect = Rect()

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        rect.set(measuredWidth/4,measuredHeight/4,measuredWidth/4*3,measuredHeight/4*3)
        canvas?.drawRect(rect,mBackgroudPaint)
        canvas?.drawText(text,measuredWidth/2F,measuredHeight/2F,mTextPain)
    }





}