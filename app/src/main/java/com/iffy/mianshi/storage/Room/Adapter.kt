package com.iffy.mianshi.storage.Room

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iffy.mianshi.R

class Adapter : RecyclerView.Adapter<ImageViewHolder>() {
    var data: ArrayList<String> = ArrayList()
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        context = parent.context
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recyclerview_item_viewmodel,
                parent,false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(context).load(data[position]).into(holder.imgview)
    }

}

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var imgview = itemView.findViewById<ImageView>(R.id.img)

}