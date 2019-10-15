package com.iffy.mianshi.alertpopupdialog

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.PopupWindowCompat
import com.iffy.mianshi.R

class PopupDialog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tv = TextView(this)
        tv.height = 50
        tv.height = 50
        tv.setText("我掉下来了")
        var pw = PopupWindow(tv,200,200)
        pw.showAsDropDown(findViewById(R.id.activity_btn))

    }
}