package com.iffy.module_view.dispatch.viewPagerConflict


import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.iffy.module_view.R
import kotlinx.android.synthetic.main.activity_conflict.*
import java.util.*
import kotlin.collections.ArrayList

class ViewPagerConflictActivity:AppCompatActivity() {
    val TAG = "ViewPagerActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        classLoader
        setContentView(R.layout.activity_conflict)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e(TAG,"onTouchEvent")
        return super.onTouchEvent(event)
    }

    override fun onResume() {
        super.onResume()
        //fragmentStatePagerAdapter在销毁fragment后 由于fragmentList持有
        //fragment的引用 导致Fragment不能被回收
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(ContentFragment("第一页"))
        fragmentList.add(BannerFragment())//这里嵌套了viewPager 滑动到头 会滑到第二页
        fragmentList.add(ContentFragment("第二页"))
        fragmentList.add(ContentFragment("第三页"))

        val fragmentClassList = ArrayList<Class<out Fragment>>()
        fragmentClassList.add(ContentFragment::class.java)


        val titleList = ArrayList<String>()
        titleList.add("A")
        titleList.add("广告")
        titleList.add("B")
        titleList.add("C")

        vp.adapter = ContentViewPagerAdapter(supportFragmentManager,0,fragmentList,titleList)
        tab.setupWithViewPager(vp)


    }
}