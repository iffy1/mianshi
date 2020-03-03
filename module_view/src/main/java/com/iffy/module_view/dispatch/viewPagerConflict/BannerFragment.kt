package com.iffy.module_view.dispatch.viewPagerConflict

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iffy.module_view.R
import kotlinx.android.synthetic.main.banner_fragment.view.*

//广告滚动
class BannerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.banner_fragment, container, false)

        val urlList = ArrayList<String>()
        urlList.add("https://eimg.smzdm.com/202002/28/5e58e8e190d781731.png")
        urlList.add("https://eimg.smzdm.com/202002/25/5e550ddf695ec5009.png")
        urlList.add("https://eimg.smzdm.com/202002/21/5e4fcf44135c71944.png")
        urlList.add("https://y.zdmimg.com/201910/09/5d9d9bf5065912271.jpg_d200.jpg")


        view.banner_vp.adapter = BannerViewPagerAdapter(urlList)
        return view
    }
}