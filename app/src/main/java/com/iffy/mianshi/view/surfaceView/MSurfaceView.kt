package com.iffy.mianshi.view.surfaceView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PixelFormat
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

//构造函数应该用参数最多的那个
class MSurfaceView(context: Context, attributeSet: AttributeSet?, defaultStyle: Int) :
    SurfaceView(context, attributeSet, defaultStyle), SurfaceHolder.Callback, Runnable {

    override fun run() {
        while (true) {
            if(canDraw){
                draw()
                xx += 10
                Thread.sleep(200)
            }
        }
    }

    var xx = 0.0f
    var yy = 0.0f
    var mPaint = Paint()
    var canDraw = true

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context) : this(context, null, 0)

    init {
        holder.addCallback(this)
        mPaint.setColor(Color.RED)

        //背景色透明
        setZOrderOnTop(true)
        getHolder().setFormat(PixelFormat.TRANSLUCENT)
        Thread(this).start()
    }



    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
    }

    override fun surfaceCreated(p0: SurfaceHolder?) {
    }

    fun draw() {
        lateinit var mCanvas: Canvas
        try {
            mCanvas = holder.lockCanvas()
            mPaint.setColor(Color.RED)
            mPaint.setStrokeWidth(100f)
            mCanvas.drawPoint(xx, yy, mPaint)
            mPaint.setColor(Color.BLUE)
            mCanvas.drawText("我是surfaceView", xx, yy + 100, mPaint)
            holder.unlockCanvasAndPost(mCanvas)
        } catch (e: Exception) {

        } finally {

        }
    }

    var detector = GestureDetector(context,object: GestureDetector.OnGestureListener{
        override fun onShowPress(e: MotionEvent?) {
            println("onShowPress")
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            println("onSingleTapUp")
            canDraw = !canDraw
            return true
        }

        override fun onDown(e: MotionEvent?): Boolean {
            println("onDown")
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            println("onFling")
            return true
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            println("onScroll")
            return true
        }

        override fun onLongPress(e: MotionEvent?) {
            println("onLongPress")
        }

    })


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("iffy", "onTouchEvent")
        detector.onTouchEvent(event)
        return true
    }
}


