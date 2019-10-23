package com.iffy.mianshi.viewCustomize

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.iffy.mianshi.R

class MyView : View {
    var width_cus = 0f
    var mContext:Context

    //当我们在代码中使用new 对象的方式创建一个自定View的时候，只能使用一个参数的构造函数。
    constructor(context: Context) : this(context, null) {
        println("我是第一个构造函数")
    }

    //当我们在布局文件里面使用自定义View的时候，调用的是两个参数的构造函数
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        println("我是第二个构造函数")
        var sa = context.obtainStyledAttributes(attrs, R.styleable.MyView)
        width_cus = sa.getDimension(R.styleable.MyView_stroke_width, -0f)
        println("x is $x | width_cus is $width_cus")

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
        println("MyView onMeasure widthMeasureSpec: $widthMeasureSpec")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    //在onDraw中进行View的绘制。
    //，在onDraw方法里面尽量不要新建对象和做耗时操作，因为onDraw经常会被频繁调用，
    // 新建对象会触发垃圾回收导致内存抖动影响性能。耗时操作会导致在一个绘制周期内无法完成所有的绘制工作从而出现丢帧问题。
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var pen = Paint()
        pen.setColor(mContext.resources.getColor(R.color.notification_icon_bg_color,null))
        canvas?.drawText("haha",x+width_cus,y+10,pen)
    }

    //在onLayout方法中对一些数据进行处理。
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

}


