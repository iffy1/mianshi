package com.iffy.mianshi.viewCustomize

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R
import org.jetbrains.anko.contentView

class CustomizeViewActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        println("onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customize_view)
    }

    override fun onResume() {
        super.onResume()
        println("onResume")
        var view = MyView(this)

    }
}