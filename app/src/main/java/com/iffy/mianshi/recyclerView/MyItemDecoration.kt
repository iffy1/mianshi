package com.iffy.mianshi.recyclerView

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MyItemDecoration : RecyclerView.ItemDecoration() {
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        var painter = Paint()
        painter.color = Color.YELLOW
        painter.textSize = 20f
        c.drawText("我在上面", 0f, 40f, painter)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        var painter = Paint()
        painter.color = Color.BLUE
        painter.textSize = 20f
        c.drawText("我在下面", 0f, 20f, painter)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(15, 15, 15, 15)
    }
}