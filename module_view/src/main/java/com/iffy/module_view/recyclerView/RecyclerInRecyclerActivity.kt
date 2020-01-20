package com.iffy.module_view.recyclerView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iffy.module_view.R

class RecyclerInRecyclerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_qiantao)

        var data = ArrayList<String>()
        for (i in 1..20) {
            data.add("Recycler $i")
        }

        var adapter = RecyclerInRecyclerAdapter(data)

        //垂直列表
        var recyclerViewA = findViewById<RecyclerView>(R.id.recyclerViewA)
        var lm = LinearLayoutManager(this)
        lm.orientation = RecyclerView.VERTICAL
        recyclerViewA.layoutManager = lm
        recyclerViewA.adapter = adapter

        //外围装饰
        recyclerViewA.addItemDecoration(MyItemDecoration())
    }
}