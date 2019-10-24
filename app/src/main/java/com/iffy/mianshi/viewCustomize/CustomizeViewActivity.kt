package com.iffy.mianshi.viewCustomize

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R
import org.jetbrains.anko.contentView
import org.jetbrains.anko.sdk25.coroutines.onClick

class CustomizeViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        println("onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customize_view)
        var circleProgress = findViewById<CircleProgress>(R.id.customize_progress)
        circleProgress.percentage = 50
        circleProgress.titleText = "运动消耗"
        circleProgress.value = 1000

        var tv = findViewById<TextView>(R.id.precent_value)
        var set_btn = findViewById<Button>(R.id.set_value_btn)
        set_btn.setOnClickListener {
            var value = Integer.parseInt(tv.text.toString())
            if(value in 0..100){
                circleProgress.percentage = value
                circleProgress.value = value * 20
                circleProgress.startCircleProgressAnim()
                circleProgress.startValueAnim()
            }else{
                Toast.makeText(this,"请输入0-100的数字",Toast.LENGTH_LONG).show()
            }

        }


    }

    override fun onResume() {
        super.onResume()
        println("onResume")
        //var view = MyView(this)

    }
}