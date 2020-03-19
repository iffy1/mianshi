package com.iffy.mianshi.alertpopupdialog

import android.os.Bundle
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R


import android.content.Context
import android.telephony.TelephonyManager
import kotlinx.android.synthetic.main.activity_main.*


class PopupDialog : AppCompatActivity() {
    lateinit var tv:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = TextView(this)
        tv.height = 50
        tv.height = 50
        tv.setText("我掉下来了")

//        var pw = PopupWindow(tv,200,200)
//        pw.showAsDropDown(findViewById(R.id.activity_btn))

        Thread(Runnable {
            Thread.sleep(5000)
            activity_tv.text = "222"
        }).start()



    }


}