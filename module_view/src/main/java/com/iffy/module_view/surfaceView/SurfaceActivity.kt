package com.iffy.module_view.surfaceView


import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.iffy.module_base.BaseActivity
import com.iffy.module_view.R


class SurfaceActivity : BaseActivity() {
    override fun getContentId(): Int {
        return R.layout.activity_surface
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_surface)

        //动态生成
//        this.getWindow()
//            .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        var s = MSurfaceView(this)
//        //s.setBackgroundColor(Color.WHITE)
//        //s.background = getDrawable(R.drawable.ic_launcher_background)
//
//        setContentView(s)

        var list = findViewById<ListView>(R.id.list)
        list.adapter = MyAdapter(this)


    }


    class MyAdapter(c: Context) : BaseAdapter() {
        var context: Context
        var inflater: LayoutInflater

        init {
            context = c
            inflater = LayoutInflater.from(c)
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            return inflater.inflate(R.layout.list_item_surface, null)
        }

        override fun getItem(p0: Int): Any {
            return 0
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return 20
        }

    }


}
