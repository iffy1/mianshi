package com.iffy.module_view.viewCustomize

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.iffy.module_view.R


class MyView : View {

    var mContext: Context
    var mBound = Rect()
    var mPaint = Paint()
    var text = "haha"
    //自定义属性
    var mTextClor = 0
    var mTextSize = 0f

    //当我们在代码中使用new 对象的方式创建一个自定View的时候，只能使用一个参数的构造函数。
    @RequiresApi(Build.VERSION_CODES.M)
    constructor(context: Context) : this(context, null) {
        println("我是第一个构造函数")
    }

    //当我们在布局文件里面使用自定义View的时候，调用的是两个参数的构造函数
    @RequiresApi(Build.VERSION_CODES.M)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        println("我是第二个构造函数")
        var sa = context.obtainStyledAttributes(attrs, R.styleable.MyView)
        //获取attr.xml里面的自定义属性
        mTextSize = sa.getDimension(R.styleable.MyView_my_text_size, -0f)
        mTextClor = sa.getColor(
            R.styleable.MyView_my_text_color,
            mContext.resources.getColor(R.color.notification_icon_bg_color, null)
        )


        //TypedArray使用完一定要回收，否则会造成内存泄漏。
        sa.recycle()

    }

    //无论是在代码中new一个CustomView实例，还是在布局文件中使用，
    // 最终都会调用三个参数的构造函数，所以我们可以在三个参数的构造函数里面，做一些初始化操作和获取自定义属性。
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mContext = context
        println("我是第三个构造函数")
    }

    //在onMeasure方法中根据测量模式设置View默认的宽高。
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        println("MyDispatchView onMeasure widthMeasureSpec: $widthMeasureSpec")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        initPaint()
    }

    private fun initPaint() {
        mPaint.style = Paint.Style.FILL
        mPaint.textSize = mTextSize
        mPaint.color = mTextClor

    }

    //在onDraw中进行View的绘制。
    //，在onDraw方法里面尽量不要新建对象和做耗时操作，因为onDraw经常会被频繁调用，
    // 新建对象会触发垃圾回收导致内存抖动影响性能。耗时操作会导致在一个绘制周期内无法完成所有的绘制工作从而出现丢帧问题。
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //通过mBound获取文子高度
        mPaint.getTextBounds(text, 0, text.length, mBound)
        canvas?.drawText(
            "haha",
            getWidth() / 2 - mBound.width() / 2.0f,
            getHeight()/ 2.0f,
            mPaint
        )
    }

    //在onLayout方法中对一些数据进行处理。
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

}


