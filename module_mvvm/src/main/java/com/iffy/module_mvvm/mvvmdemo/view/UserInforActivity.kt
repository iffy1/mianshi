package com.iffy.module_mvvm.mvvmdemo.view

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.iffy.module_base.BaseActivity
import com.iffy.module_mvvm.R
import com.iffy.module_mvvm.databinding.ActivityUserInfoBinding
import com.iffy.module_mvvm.mvvmdemo.viewModel.UserViewModel

class UserInforActivity:BaseActivity() {
    override fun getContentId(): Int {
        return  0
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = DataBindingUtil.setContentView<ActivityUserInfoBinding>(this, R.layout.activity_user_info)
        binding.lifecycleOwner = this
        val userVm = ViewModelProviders.of(this).get(UserViewModel::class.java)
        binding.userInfo = userVm.getUserInfo().value
    }
}