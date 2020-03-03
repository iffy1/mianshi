package com.iffy.module_view.dispatch.viewPagerConflict

import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.iffy.module_view.R
import kotlinx.android.synthetic.main.banner_item.view.*

//上述四个方法是必须得到重载的，其他的不管，我们今天只看
//instantiateItem(ViewGroup, int)
//destroyItem(ViewGroup, int, Object)
//getCount()
//isViewFromObject(View, Object)

class BannerViewPagerAdapter(
    var urlList: List<String>
) : PagerAdapter() {
    var viewMap = SparseArray<View>()
    val TAG = this::class.java.simpleName

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        Log.e(TAG, "instantiateItem $position")
        //有缓存的话 直接用缓存
        if (viewMap[position] == null) {
            var viewItem =
                LayoutInflater.from(container.context)
                    .inflate(R.layout.banner_item, container, false)
            Glide.with(container.context).load(urlList[position]).into(viewItem.guanggao)
            viewItem.guanggao_dizhi.text = urlList[position]
            viewMap.put(position, viewItem)
            container.addView(viewMap.get(position))
        }
        return viewMap.get(position)
    }

    override fun getCount(): Int {
        return urlList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        Log.e(TAG, "destroyItem $position")
//        if (viewMap[position] != null) {
//                viewMap.remove(position)
//        }
        //container.removeViewAt(position)
    }


}