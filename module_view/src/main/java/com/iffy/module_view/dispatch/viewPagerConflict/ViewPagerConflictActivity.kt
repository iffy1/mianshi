package com.iffy.module_view.dispatch.viewPagerConflict


import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.iffy.module_view.R
import kotlinx.android.synthetic.main.activity_conflict.*
import java.util.*
import kotlin.collections.ArrayList

class ViewPagerConflictActivity : AppCompatActivity() {
    val TAG = "ViewPagerActivity"

    //建议在这种多个页面连续使用MapView的情景，
    // 使用一个主Activity(带MapView控件)+多个fragment来实现你的业务逻辑，
    // fragment尽量复用主Activity的MapView。顺便说下，fragment开销很小，
    // 而且它也可以被复用，所以在有些场景下，建议多使用fragment，会感到页面切换如丝般顺滑
    lateinit var sharedImage:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conflict)

        sharedImage = ImageView(this)
        sharedImage.setImageResource(R.drawable.ic_nav_about)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e(TAG, "onTouchEvent")
        return super.onTouchEvent(event)
    }

    fun getImageView(): ImageView {
        return sharedImage
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

        val titleList = ArrayList<String>()
        titleList.add("A")
        titleList.add("广告")
        titleList.add("B")
        titleList.add("C")

        vp.adapter = ContentViewPagerAdapter(supportFragmentManager, 0, fragmentList, titleList)
        //绑定tabview和viewPager
        tab.setupWithViewPager(vp)


    }
}