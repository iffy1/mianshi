package com.iffy.mianshi.view.viewEvent

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R



class ViewClickActivity : AppCompatActivity() {
    lateinit var gest: GestureDetector
    lateinit var btna: Button

    override fun onResume() {
        super.onResume()
        //我们知道在oncreate中View.getWidth和View.getHeight无法获得一个view的高度和宽度，这是因为View组件 布局要在onResume回调后完成。
        println("onResume btna 的宽度 ${btna.width}")
        //还没展示UI
        Thread.sleep(3000)
    }

    override fun onPause() {
        super.onPause()
        //我们知道在oncreate中View.getWidth和View.getHeight无法获得一个view的高度和宽度，这是因为View组件 布局要在onResume回调后完成。
        println("onPause btna 的宽度 ${btna.width}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_click)
        btna = findViewById<Button>(R.id.btn_a)
        //我们知道在oncreate中View.getWidth和View.getHeight无法获得一个view的高度和宽度，这是因为View组件 布局要在onResume回调后完成。
        println("onCreate btna 的宽度 ${btna.width}")
        //获取部件宽度方法1
        btna.post(Runnable {
            println(" btna.post btna 的宽度 ${btna.width}")
        })
        //获取部件宽度方法2
        btna.viewTreeObserver.addOnGlobalLayoutListener {
            println(" GlobalLayoutListener btna 的宽度 ${btna.width}")
        }

        //直接算(不准)
        calculateWidth(btna)

        var btnb = findViewById<Button>(R.id.btn_b)
        var viewgroup = findViewById<View>(R.id.viewgroup)
        gest = GestureDetector(this, object : GestureDetector.OnGestureListener {
            override fun onShowPress(e: MotionEvent?) {
                println("onShowPress")
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                println("onSingleTapUp")
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
        viewgroup.setOnTouchListener { v, event ->
            println("MyView [ViewGroup] onTocuh $event")
            false
        }

        btna.setOnClickListener {
            println("btna [子View] onClick")
            Toast.makeText(this, "btnA clicked", Toast.LENGTH_LONG).show()
        }

        btna.setOnTouchListener { v, event ->
            println("btna [子View] onTocuh $event")
            false
        }

        btnb.setOnClickListener {
            println("btnb [子View] onClick")
            Toast.makeText(this, "btnB clicked", Toast.LENGTH_LONG).show()
        }
        btnb.isClickable = false

        btnb.setOnTouchListener { v, event ->
            println("btnb [子View] onTocuh $event")
            false
        }
    }

    private fun calculateWidth(btna: Button?) {
        val intw = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val inth = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        btna?.measure(intw, inth)
        val intwidth = btna?.measuredWidth
        println(" calculateWidth 强算（不准） btna 的宽度 $intwidth")
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        println("Activity [Activity] onTouchEvent $event")
        Toast.makeText(this, "ViewClickActivity clicked", Toast.LENGTH_LONG).show()
        return gest.onTouchEvent(event)
    }
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        println("Activity [Activity] dispatchTouchEvent $ev")
        return super.dispatchTouchEvent(ev)
    }

    class MyView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
        LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

        constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(
            context,
            attrs,
            defStyleAttr,
            0
        )

        constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

        constructor(context: Context?) : this(context, null)

        override fun onTouchEvent(event: MotionEvent?): Boolean {
            println("MyView [ViewGroup] onTouchEvent $event")
            return false
        }

        override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
            println("MyView [ViewGroup] dispatchTouchEvent $ev")
            return super.dispatchTouchEvent(ev)
        }


    }
}