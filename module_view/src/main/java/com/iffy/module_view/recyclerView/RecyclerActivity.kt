package com.iffy.module_view.recyclerView

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.iffy.module_base.BaseActivity
import com.iffy.module_view.R


class RecyclerActivity : BaseActivity() {
    override fun getContentId(): Int {
        return R.layout.activity_recycler
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_recycler)

        var data = ArrayList<String>()
        for (i in 1..200) {
            data.add("Recycler $i")
        }

        var adapter = RecycleAdapter(data)

        //垂直列表
        var recyclerViewA = findViewById<RecyclerView>(R.id.recyclerViewA)
        var lm = LinearLayoutManager(this)
        lm.orientation = RecyclerView.VERTICAL
        recyclerViewA.layoutManager = lm
        recyclerViewA.adapter = adapter

        //外围装饰
        recyclerViewA.addItemDecoration(MyItemDecoration())


        //水平列表
        var recyclerViewB = findViewById<RecyclerView>(R.id.recyclerViewB)
        var lmb = LinearLayoutManager(this)
        lmb.orientation = RecyclerView.HORIZONTAL
        recyclerViewB.layoutManager = lmb
        recyclerViewB.adapter = adapter
        //外围装饰
        recyclerViewB.addItemDecoration(MyItemDecoration())

        //垂直瀑布
        var recyclerViewC = findViewById<RecyclerView>(R.id.recyclerViewC)
        var pubuLayoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        recyclerViewC.layoutManager = pubuLayoutManager
        recyclerViewC.adapter = adapter

        //外围装饰
        recyclerViewC.addItemDecoration(MyItemDecoration())

        //添加拖拽处理
        var ith = ItemTouchHelper(MyItemTouchHelper(adapter))
        ith.attachToRecyclerView(recyclerViewA)


    }
}