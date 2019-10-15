package com.iffy.mianshi.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iffy.mianshi.R

class RecyclerInRecyclerAdapter(var data: ArrayList<String>) : RecyclerView.Adapter<RecyclerInRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerInRecyclerViewHolder {
        return RecyclerInRecyclerViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recyclerinrecyclerview_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerInRecyclerViewHolder, position: Int) {
        holder.setData()
    }

    //公开接口给MyItemTouchHelper 做数据位置交换 移动
    fun onItemMoved(from: Int, to: Int) {
        var temp = data[to]
        data[to] = data[from]
        data[from] = temp
        notifyItemMoved(from, to)
    }

    //公开接口给MyItemTouchHelper 做数据删除 拖拽
    fun onItemSwiped(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }
}
