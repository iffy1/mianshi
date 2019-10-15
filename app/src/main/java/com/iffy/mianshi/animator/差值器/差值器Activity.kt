package com.iffy.mianshi.animator.差值器

import android.animation.ObjectAnimator
import android.graphics.Point
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R

class 差值器Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chazhiqi)
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