package com.iffy.module_mvvm.twoWayDataBinding.view


import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.iffy.module_base.BaseActivity
import com.iffy.module_mvvm.R
import com.iffy.module_mvvm.databinding.ActivityTwoWayMainBinding
import com.iffy.module_mvvm.twoWayDataBinding.viewModel.TextViewTwoWayContent_MutableLiveData
import com.iffy.module_mvvm.twoWayDataBinding.viewModel.TextViewTwoWayContent_Observable

//实现databinding
//可以使用androidx.lifecycle.MutableLiveData 可以感知生命周期
// 或者 androidx.databinding.ObservableField
class MainTwoWayActivity : BaseActivity() {
    override fun getContentId(): Int {
        return R.layout.activity_two_way_main
    }

    override fun onResume() {
        super.onResume()

        //系统编译时候会帮你生成ActivityMainBinding的类
        val activityMainBinding: ActivityTwoWayMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_two_way_main)


        //初始化view model observable 不需要绑定生命周期
        val textObservable = ObservableField<String>("初始值Observable")
        val defaultTextObservable = TextViewTwoWayContent_Observable(textObservable)
        //将activity和 data绑定 Activity显示时会自动显示"初始值"
        activityMainBinding.textviewObservabel = defaultTextObservable



        //初始化view model LiveData
        val textMutableLiveData = MutableLiveData<String>()
        textMutableLiveData.value = "初始值MutableLiveData"
        textMutableLiveData.observe(this, Observer {

        })
        val defaultTextMutable = TextViewTwoWayContent_MutableLiveData(textMutableLiveData)
        activityMainBinding.textviewMutable = defaultTextMutable
        //绑定生命周期
        activityMainBinding.lifecycleOwner = this

        activityMainBinding.editText.addTextChangedListener{
            println("text changed ${it.toString()}")
            //post 可以跨线程
            textMutableLiveData.postValue(it.toString())
            //set只能主线程
            textMutableLiveData.value = it.toString()
            textObservable.set(it.toString())
        }


    }

}
