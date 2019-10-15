package com.iffy.mianshi.popupwindow


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.iffy.mianshi.R

class PopUpWinActivity : AppCompatActivity() {
    lateinit var pwbtn: Button
    lateinit var albtn: Button
    lateinit var tv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        println("--------onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup)
        pwbtn = findViewById(R.id.pwbtn)
        tv = findViewById(R.id.tv)
        pwbtn.setOnClickListener {
            var ttv = TextView(this)
            ttv.height = 100
            ttv.width = 100
            ttv.setText("谈谈")
            var pw = PopupWindow(ttv, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            pw.setBackgroundDrawable(resources.getDrawable(R.drawable.ic_launcher_background))
            //pw.showAtLocation(tv,1,5,5)
            println("--------showAsDropDown A")
            pw.showAsDropDown(tv)
            println("--------showAsDropDown B")
        }
        albtn = findViewById(R.id.alertd)
        albtn.setOnClickListener {
            println("--------showAlertDialog A")
            AlertDialog.Builder(this).setMessage("我是弹框").show()
            println("--------showAlertDialog B")
        }
    }

    override fun onResume() {
        println("--------onResume")
        super.onResume()
    }

    override fun onPause() {
        println("--------onPause")
        super.onPause()
    }

    override fun onStop() {
        println("--------onStop")
        super.onStop()
    }
}
