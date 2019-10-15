package com.iffy.mianshi.序列化

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class ActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var duckb = intent.getParcelableExtra<DuckB>("对象B")
        var duckc = intent.getSerializableExtra("对象C")
        duckc as DuckC
        Log.e("iffy", duckb.name)
        Log.e("iffy", duckc.name)
    }
}