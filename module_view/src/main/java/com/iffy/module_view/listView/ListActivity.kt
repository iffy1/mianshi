package com.iffy.module_view.listView

import android.database.MatrixCursor
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity
import com.iffy.module_view.R


class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        /*---------------------ListA SimpleAdapter---------------------*/
        //功能强大的Adapter，用于将XML中控件绑定为列表项的数据源
        var listA = findViewById<ListView>(R.id.listA)
        var title = arrayOf(
            "SimpleListAdapter第一条",
            "SimpleListAdapter第二条",
            "SimpleListAdapter第三条",
            "SimpleListAdapter第四条",
            "SimpleListAdapter第五条",
            "SimpleListAdapter第六条",
            "SimpleListAdapter第七条",
            "SimpleListAdapter第八条",
            "SimpleListAdapter第九条",
            "SimpleListAdapter第十条",
            "SimpleListAdapter第十一条",
            "SimpleListAdapter第十二条"
        )
        var content = arrayOf("第1条", "第2条", "第3条", "第4条", "第5条", "第6条", "第7条", "第8条", "第9条", "第10条", "第11条", "第12条")
        var image = arrayOf(
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
        )
        var data = ArrayList<Map<String, Any>>()
        for (i in title.indices) {
            var map = HashMap<String, Any>()
            map["Title"] = title[i]
            map["Content"] = content[i]
            map["Image"] = image[i]
            data.add(map)
        }

        var simpleAdapter = SimpleAdapter(
            this,
            data,
            R.layout.list_item_simple_item,
            arrayOf("Title", "Content", "Image"),
            intArrayOf(R.id.simpleAdapterItemTitle, R.id.simpleAdapterItemContent, R.id.simpleAdapterImg)
        )

        listA.adapter = simpleAdapter
        /*---------------------ListA SimpleAdapter---------------------*/

        /*---------------------ListB ArrayAdapter---------------------*/
        //ArrayAdapter较为简单，易用，但每个列表项只能是TextView，功能实现的局限性非常大
        var listB = findViewById<ListView>(R.id.listB)
        var arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter(this, R.layout.list_item_array_item, R.id.arrayAdapterItemTitle)
        for (i in 1..20) {
            arrayAdapter.add("ArrayAdapter $i")
        }
        listB.adapter = arrayAdapter
        /*---------------------ListB ArrayAdapter---------------------*/

        /*---------------------ListC CursorAdapter---------------------*/
        var listC = findViewById<ListView>(R.id.listC)
        var idArray = intArrayOf(R.id.cursorAdapterItemTitle, R.id.cursorAdapterItemContent)

        //表名
        var stringArray = arrayOf("_id", "title")
        var cursor = MatrixCursor(stringArray)
        for (l in 1..20) {
            var ass = arrayOf(l, "CursorAdapter $l")
            cursor.addRow(ass)
        }

        var cursorAdapter = SimpleCursorAdapter(this, R.layout.list_item_cursor_item, cursor, stringArray, idArray, 0)
        listC.adapter = cursorAdapter

        /*---------------------ListC CursorAdapter---------------------*/


        /*---------------------ListD BaseAdapter---------------------*/
        var listD = findViewById<ListView>(R.id.listD)
        var dataD = arrayOf(
            "BaseAdapter 1", "BaseAdapter 2", "BaseAdapter 3", "BaseAdapter 4", "BaseAdapter 5", "BaseAdapter 6"
            , "BaseAdapter 7", "BaseAdapter 8", "BaseAdapter 9", "BaseAdapter 10", "BaseAdapter 11"
            , "BaseAdapter 12", "BaseAdapter 13", "BaseAdapter 14", "BaseAdapter 15", "BaseAdapter 16"
        )
        var baseAdpter = MyBaseAdapter(this, dataD)
        listD.adapter = baseAdpter
        /*---------------------ListD BaseAdapter---------------------*/
    }

}