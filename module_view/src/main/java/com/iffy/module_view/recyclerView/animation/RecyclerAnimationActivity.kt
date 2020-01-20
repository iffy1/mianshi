package com.iffy.module_view.recyclerView.animation

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.iffy.module_view.R
import com.iffy.module_view.recyclerView.RecycleAdapter

class RecyclerAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_animation)

        val data = ArrayList<String>()
        for (i in 1..200) {
            data.add("Recycler $i")
        }

        val adapter = RecycleAdapter(data)

        //垂直列表
        val recyclerViewA = findViewById<RecyclerView>(R.id.recyclerViewA)
        val lm = LinearLayoutManager(this)
        lm.orientation = RecyclerView.VERTICAL
        recyclerViewA.layoutManager = lm
        recyclerViewA.adapter = adapter

        val addbtn = findViewById<Button>(R.id.add)
        addbtn.setOnClickListener {
            data.add(5,"我是新来的")
            adapter.notifyItemInserted(5)
        }

        val delete = findViewById<Button>(R.id.delete)
        delete.setOnClickListener {
            data.removeAt(0)
            adapter.notifyItemRemoved(0)
        }
        var itemAni = MyDefaultItemAnimator()
        itemAni.addDuration = 1000
        itemAni.removeDuration = 1000
        recyclerViewA.setItemAnimator(itemAni)

    }
}