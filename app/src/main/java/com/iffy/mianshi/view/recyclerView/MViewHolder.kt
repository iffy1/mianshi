package com.iffy.mianshi.view.recyclerView

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iffy.mianshi.R

class MViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tv: TextView = itemView.findViewById(R.id.recycleItemTitle)
    var img:ImageView = itemView.findViewById(R.id.recycleItemImage)
    init {
        tv.setOnClickListener {
            Log.e("iffy","${tv.text}:我被点击了")
        }
    }

    fun setText(text: String) {
        tv.text = text
    }

    fun setImage(id:Int){
        img.setImageResource(id)
    }
}