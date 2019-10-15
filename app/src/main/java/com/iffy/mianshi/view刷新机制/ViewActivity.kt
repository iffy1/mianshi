package com.iffy.mianshi.view刷新机制

import android.content.Context
import android.graphics.Canvas
import android.os.AsyncTask
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R

class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        var v = findViewById<MView>(R.id.myview)
        Thread(Runnable {
            for (i in 1..1000) {
                v.left = i
                v.top = i
                v.bottom = v.bottom - i
                v.right = v.right - i
                v.postInvalidate()
                v.requestFocus()
                Thread.sleep(50)
                Log.e("iffy", "--------------$i")
            }
        }).start()
    }
}

internal class MView(var coontext: Context, var attrs: AttributeSet) : View(coontext, attrs) {
    override fun requestLayout() {
        super.requestLayout()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("iffy", "x: ${event!!.x} |y:${event!!.y}")
        return super.onTouchEvent(event)
    }


}