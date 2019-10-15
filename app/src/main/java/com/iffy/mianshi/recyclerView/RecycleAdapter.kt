package com.iffy.mianshi.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iffy.mianshi.R

class RecycleAdapter(var data: ArrayList<String>) : RecyclerView.Adapter<MViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        return MViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.setText(data[position])
        holder.setImage(R.drawable.ic_launcher_background)
        //实现交错
        if (position == 0) {
            holder.itemView.layoutParams.height = holder.itemView.layoutParams.height
        }
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
