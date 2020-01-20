package com.iffy.module_animation.transition

import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionManager
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.iffy.module_animation.R
import com.iffy.module_base.BaseActivity

class SimpleTransitionDemoActivity : BaseActivity() {
    lateinit var a: Button
    lateinit var b: Button
    lateinit var c: Button
    lateinit var d: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_transition_activity)
        a = findViewById(R.id.btn_a)
        b = findViewById(R.id.btn_b)
        c = findViewById(R.id.btn_c)
        d = findViewById(R.id.btn_d)



        d.setOnClickListener {
            //TransitionManager.beginDelayedTransition(findViewById(R.id.layout_root_view),Fade())
            TransitionManager.beginDelayedTransition(findViewById(R.id.layout_root_view), Slide())
            //TransitionManager.beginDelayedTransition(findViewById(R.id.layout_root_view), Explode())
            var e:View = findViewById(R.id.layout_root_view)
            e.visibility = View.GONE
            a.visibility = View.GONE
            b.visibility = View.GONE
            c.visibility = View.GONE
        }
    }
}