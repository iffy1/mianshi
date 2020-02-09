package com.iffy.module_mvvm.mvvm.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MFragmentPagerAdapter(
    var fragments: ArrayList<Fragment>,
    var fragmentTitle: ArrayList<String>,
    fragmentManager: FragmentManager,
    behavior: Int
) : FragmentStatePagerAdapter(fragmentManager, behavior) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    //用于Tablayout显示Title文字
    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitle[position]
    }
}