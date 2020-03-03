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
import kotlinx.android.synthetic.main.fragment_a.view.*

class FragmentA : Fragment() {
    private var vm: FragmentViewModel? = null
     var mRootView:View? =null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //防止每次切换界面都要初始化界面
        if(mRootView!=null){
            return mRootView
        }
        Log.e(MVVMActivity.TAG, "onCreateView ${this.javaClass.simpleName}")
        // 对布局需要绑定的内容进行绑定
        //这里绑定只为了省去findViewById,没有把数据和xml就行绑定
        val fragmentBinding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.fragment_a,
            container,
            false
        )
        //绑定生命周期
        //fragmentBinding.lifecycleOwner = this


        //传入activity不然不能共享数据
        vm = ViewModelProviders.of(activity!!).get(FragmentViewModel::class.java)

        mRootView=fragmentBinding.root

        //设置数据监听
        vm?.getData()?.observe(this,
            Observer<String> {
                println("收到更新信息:$it")
                mRootView?.text_content_A?.text = it
            })

        //返回root
        return mRootView
    }

    //setUserVisibleHint配合viewpager使用感知fragment是否可见
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.e(MVVMActivity.TAG, "${this.javaClass.simpleName} isVisibleToUser:$isVisibleToUser")
        if (isVisibleToUser) {
            vm?.updateData("第一页添加了信息")
        }
    }


}