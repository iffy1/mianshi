package com.iffy.mianshi.view事件分发

import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R

class ViewClickActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_click)
        var btna = findViewById<Button>(R.id.btn_a)
        var btnb = findViewById<Button>(R.id.btn_b)

        btna.setOnClickListener {
            Toast.makeText(this,"btnA clicked",Toast.LENGTH_LONG).show()
        }

        btnb.setOnClickListener {
            Toast.makeText(this,"btnB clicked",Toast.LENGTH_LONG).show()
        }
        btnb.isClickable = false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        Toast.makeText(this,"ViewClickActivity clicked",Toast.LENGTH_LONG).show()
        return super.onTouchEvent(event)


    }
}