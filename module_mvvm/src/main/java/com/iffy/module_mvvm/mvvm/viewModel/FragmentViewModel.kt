package com.iffy.module_mvvm.mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentViewModel : ViewModel() {
    private var mutableLiveData = MutableLiveData<String>()

    init {
        mutableLiveData.value = "我是第一页"
    }

    //获取数据
    fun getData(): MutableLiveData<String> {
        return mutableLiveData
    }

    //更新信息
    fun updateData(text:String) {
        Thread {
            Thread.sleep(5000)
            mutableLiveData.postValue(mutableLiveData.value+text)
        }.start()

    }

}