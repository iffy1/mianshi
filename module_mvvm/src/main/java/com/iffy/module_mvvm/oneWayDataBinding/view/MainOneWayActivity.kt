package com.iffy.module_mvvm.oneWayDataBinding.view


import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.iffy.module_base.BaseActivity
import com.iffy.module_mvvm.R
import com.iffy.module_mvvm.oneWayDataBinding.viewModel.TextViewOneWayContentVM
import com.iffy.module_mvvm.databinding.ActivityOneWayMainBinding

//实现databinding
class MainOneWayActivity : BaseActivity() {
    override fun getContentId(): Int {
        return R.layout.activity_one_way_main
    }

    override fun onResume() {
        super.onResume()

        //系统编译时候会帮你生成ActivityMainBinding的类
        val activityMainBinding: ActivityOneWayMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_one_way_main)

        //初始化view model
        val text = "初始值"
        val defaultTextVM = TextViewOneWayContentVM(text)
        activityMainBinding.let {
            it.textviewVM = defaultTextVM
            //LiveData到activity使得Livedata可以感知这个activity的生命周期
            it.lifecycleOwner = this
        }

        val editText = findViewById<EditText>(R.id.editText)
        editText.addTextChangedListener {
            println("text changed ${it.toString()}")
            defaultTextVM.text = it.toString()
        }


    }

}
