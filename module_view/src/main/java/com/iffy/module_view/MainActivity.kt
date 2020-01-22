package com.iffy.module_view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.iffy.module_base.BaseActivity
import com.iffy.module_view.listView.ListActivity
import com.iffy.module_view.recyclerView.RecyclerActivity
import com.iffy.module_view.recyclerView.RecyclerInRecyclerActivity
import com.iffy.module_view.recyclerView.animation.RecyclerAnimationActivity
import com.iffy.module_view.surfaceView.SurfaceActivity
import com.iffy.module_view.viewCustomize.CustomizeViewActivity
import com.iffy.module_view.viewEvent.ViewClickActivity
import com.iffy.module_view.viewOptimiz.ViewOptActivity
import com.iffy.module_view.viewRefresh.ViewActivity

class MainActivity : BaseActivity() {
    override fun getContentId(): Int {
        return R.layout.activity_main
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun goToListActivity(v: View) {
        val i = Intent()
        i.setClass(this, ListActivity::class.java)
        startActivity(i)
    }

    fun goToRecyclerAnimationActivity(v: View) {
        val i = Intent()
        i.setClass(this, RecyclerAnimationActivity::class.java)
        startActivity(i)
    }

    fun goToRecyclerActivity(v: View) {
        val i = Intent()
        i.setClass(this, RecyclerActivity::class.java)
        startActivity(i)
    }

    fun goToRecyclerInRecyclerActivity(v: View) {
        val i = Intent()
        i.setClass(this, RecyclerInRecyclerActivity::class.java)
        startActivity(i)
    }

    fun goToSurfaceActivity(v: View) {
        val i = Intent()
        i.setClass(this, SurfaceActivity::class.java)
        startActivity(i)
    }

    fun goToCustomizeViewActivity(v: View) {
        val i = Intent()
        i.setClass(this, CustomizeViewActivity::class.java)
        startActivity(i)
    }

    fun goToViewClickActivity(v: View) {
        val i = Intent()
        i.setClass(this, ViewClickActivity::class.java)
        startActivity(i)
    }

    fun goToViewActivity(v: View) {
        val i = Intent()
        i.setClass(this, ViewActivity::class.java)
        startActivity(i)
    }

    fun goToViewOptActivity(v: View) {
        val i = Intent()
        i.setClass(this, ViewOptActivity::class.java)
        startActivity(i)
    }


}
