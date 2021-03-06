package com.iffy.module_mvvm.mvvm.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.tabs.TabLayout
import com.iffy.module_mvvm.R
import com.iffy.module_mvvm.mvvm.adapter.MFragmentPagerAdapter
import com.iffy.module_mvvm.mvvm.viewModel.FragmentViewModel
import kotlinx.android.synthetic.main.activity_mvvm.*
import kotlinx.android.synthetic.main.activity_mvvm.view.*

class MVVMActivity : FragmentActivity() {
    companion object{
        val TAG = "MVVMActivity"
    }
    lateinit var tab: TabLayout
    lateinit var vp: ViewPager
    private val fragmentData: ArrayList<Fragment> by lazy {
        ArrayList<Fragment>()
    }

    private val fragmentTitle: ArrayList<String> by lazy {
        ArrayList<String>()
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //绑定 省去findViewById,但是没有进行xml绑定
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_mvvm)
        //绑定生命周期
        binding.lifecycleOwner = this

        tab = binding.root.tab
        vp = binding.root.vp

        //初始化fragment
        initFragments()

        //创建adapter
        val adapter = MFragmentPagerAdapter(
            fragmentData, fragmentTitle,
            supportFragmentManager,
            BEHAVIOR_SET_USER_VISIBLE_HINT
        )
        vp.adapter = adapter
        //关联tablayout和view pager
        tab.setupWithViewPager(vp)

        //kotlin-android-extensions 直接调用layout id,不需要findViewById
        conslayout.setBackgroundColor(resources.getColor(R.color.color_page_bg,null))

    }


    fun initFragments() {
        Log.e(TAG,"initFragments")
        //添加title
        fragmentTitle.add("FragmentA")
        fragmentTitle.add("FragmentB")
        fragmentTitle.add("FragmentC")
        //添加fragment
        fragmentData.add(FragmentA())
        fragmentData.add(FragmentB())
        fragmentData.add(FragmentC())
    }
}