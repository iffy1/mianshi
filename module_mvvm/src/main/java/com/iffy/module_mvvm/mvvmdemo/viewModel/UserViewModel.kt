package com.iffy.module_mvvm.mvvmdemo.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iffy.module_mvvm.mvvmdemo.bean.UserInfo

class UserViewModel : ViewModel() {
    private lateinit var userInfo: MutableLiveData<UserInfo>
    fun getUserInfo(): MutableLiveData<UserInfo> {
        userInfo = MutableLiveData<UserInfo>()
        userInfo.value = UserInfo(
            "张三",
            18,
            "电工",
            "学习",
            "https://cdn2.jianshu.io/assets/default_avatar/2-9636b13945b9ccf345bc98d0d81074eb.jpg"
        )
        return userInfo
    }
}