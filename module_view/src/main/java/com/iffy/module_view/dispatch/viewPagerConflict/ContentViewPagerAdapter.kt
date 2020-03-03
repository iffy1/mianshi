package com.iffy.module_view.dispatch.viewPagerConflict

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ContentViewPagerAdapter(
    fm: FragmentManager,
    behavior: Int,
    var fragmentList: List<Fragment>,
    var tileList: List<String>
) :
    FragmentStatePagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tileList[position]
    }
}