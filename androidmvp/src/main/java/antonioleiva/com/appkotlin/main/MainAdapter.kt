package antonioleiva.com.appkotlin.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import antonioleiva.com.appkotlin.R

//MyPresenterOnClickListener 用于 adapter和presenter的通信
class MainAdapter(private val items: List<String>, private val myPresenterItemListener: MyPresenterOnClickListener) :
        RecyclerView.Adapter<MainAdapter.MainViewHolder>(), View.OnClickListener {

    override fun onClick(v: View?) {
        var position = v?.tag as Int
        myPresenterItemListener.onClick(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_main_item, parent, false) as TextView
        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item
        holder.itemView.tag = position
        holder.itemView.setOnClickListener(this)
    }


    override fun getItemCount(): Int = items.size

    class MainViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {

    }
}