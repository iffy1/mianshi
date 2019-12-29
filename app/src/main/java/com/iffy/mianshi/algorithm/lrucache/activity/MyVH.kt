package com.iffy.mianshi.algorithm.lrucache.activity

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.iffy.mianshi.R


class MyVH( item: View) : RecyclerView.ViewHolder(item) {
   var imgv = item.findViewById<ImageView>(R.id.lrurecycleItemImage)
}