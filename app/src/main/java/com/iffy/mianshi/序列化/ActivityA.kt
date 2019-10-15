package com.iffy.mianshi.序列化

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class ActivityA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var i = Intent()

        //DuckA不支持序列化 所以不能传递
        //i.putExtra("对象A", DuckA("small duck"))

        i.putExtra("对象B", DuckB("small duck B"))
        i.putExtra("对象C", DuckC("small duck C"))
        i.setClass(this, ActivityB::class.java)
        startActivity(i)
    }
}