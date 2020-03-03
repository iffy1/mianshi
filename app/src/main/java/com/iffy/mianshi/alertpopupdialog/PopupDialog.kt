package com.iffy.mianshi.alertpopupdialog

import android.os.Bundle
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R


import android.content.Context
import android.telephony.TelephonyManager


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