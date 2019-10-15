package com.iffy.mianshi.recyclerView

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class MyItemTouchHelper(var adapter: RecycleAdapter) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return makeMovementFlags(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        )
    }

    //是否可以对每个Item进行侧滑。
    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    //拖拽换位
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        Log.e("iffy", "onMoveonMoveonMove")
        adapter.onItemMoved(viewHolder.position, target.position)
        return true
    }

    //侧滑删除
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        Log.e("iffy", "onSwipedonSwipedonSwipedonSwipedonSwiped")
        adapter.onItemSwiped(viewHolder.position)
    }
}