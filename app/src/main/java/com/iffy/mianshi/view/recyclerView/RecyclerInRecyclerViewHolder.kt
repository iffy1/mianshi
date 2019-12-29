package com.iffy.mianshi.view.recyclerView

import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iffy.mianshi.R

class RecyclerInRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var rv: RecyclerView = itemView.findViewById(R.id.inRecyclerView)

    fun setData() {
        var data = ArrayList<String>()
        for (i in 1..200) {
            data.add("Recycler $i")
        }

        var adapter = RecycleAdapter(data)

        var lm = LinearLayoutManager(itemView.context)
        lm.orientation = RecyclerView.HORIZONTAL
        rv.layoutManager = lm
        rv.adapter = adapter

        //添加拖拽处理
        var ith = ItemTouchHelper(MyItemTouchHelper(adapter))
        ith.attachToRecyclerView(rv)
    }

}