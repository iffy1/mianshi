package com.iffy.module_mvvm.mvvm.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.iffy.module_mvvm.R
import com.iffy.module_mvvm.mvvm.viewModel.FragmentViewModel
import kotlinx.android.synthetic.main.fragment_b.view.*

class FragmentB : Fragment() {
    private var vm: FragmentViewModel? = null
     var mRootView: View?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //防止每次切换界面都要初始化界面
        if (mRootView != null) {
            return mRootView
        }
        Log.e(MVVMActivity.TAG, "onCreateView ${this.javaClass.simpleName}")
        //绑定fragment布局
        val dataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.fragment_b,
            container,
            false
        )

        //获取viewModel 传入activity不然不能共享数据
        vm = ViewModelProviders.of(activity!!).get(FragmentViewModel::class.java)

        mRootView = dataBinding.root
        //观察数据变化
        vm?.getData()?.observe(this, object : Observer<String> {
            override fun onChanged(t: String) {
                mRootView?.text_content_B?.text = t
            }
        })

        return mRootView
    }

    //配合viewpager监听fragmemt切换
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.e(MVVMActivity.TAG, "${this.javaClass.simpleName} isVisibleToUser:$isVisibleToUser")
        if (isVisibleToUser) {
            vm?.updateData("第二页添加了信息")
        }
    }


}