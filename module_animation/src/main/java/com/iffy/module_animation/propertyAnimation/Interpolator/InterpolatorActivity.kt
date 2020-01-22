package com.iffy.module_animation.propertyAnimation.Interpolator

import android.animation.ObjectAnimator
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import com.iffy.module_animation.R
import com.iffy.module_base.BaseActivity

class InterpolatorActivity : BaseActivity() {
    override fun getContentId(): Int {
        return R.layout.activity_chazhiqi
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var btn = findViewById<Button>(R.id.btn_chazhiqi)
        btn.setOnClickListener {
            var display = this.windowManager.defaultDisplay
            var point = Point()
            display.getSize(point)

            var ani = ObjectAnimator.ofFloat(btn, "x", 1f, (point.x.toFloat() - btn.width))
            ani.setDuration(5000)
            ani.interpolator = MyInterpolator()
            ani.start()
        }
    }
}