package com.iffy.mianshi.list

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.iffy.mianshi.R


class MyBaseAdapter(context: Context, data: Array<String>) : BaseAdapter() {
    var mData: Array<String> = data
    var mContext = context

    override fun getView(position: Int, convertView: View?, p2: ViewGroup?): View {
        var view: View? = convertView
        //保存item view中的部件e.g. textview imageview免去 findview
        var ttv: ViewHolder
        if (view == null) {
            view = View.inflate(mContext, R.layout.list_item_base_item, null)
            var tv = view!!.findViewById<TextView>(R.id.baseAdapterItemTitle)
            ttv = ViewHolder()
            ttv.titleTV = tv
            view.tag = ttv
        } else {
            ttv = view.tag as ViewHolder
        }
        ttv.titleTV!!.text = mData[position]
        return view
    }

    override fun getItem(p0: Int): Any {
        return mData[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return mData.size
    }

    internal class ViewHolder {
        var titleTV: TextView? = null
    }
}