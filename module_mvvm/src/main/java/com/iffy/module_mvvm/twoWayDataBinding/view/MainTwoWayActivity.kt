package com.iffy.module_mvvm.twoWayDataBinding.view


import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.iffy.module_base.BaseActivity
import com.iffy.module_mvvm.R
import com.iffy.module_mvvm.databinding.ActivityTwoWayMainBinding
import com.iffy.module_mvvm.twoWayDataBinding.viewModel.TextViewTwoWayContent

//实现databinding
class MainTwoWayActivity : BaseActivity() {
    override fun getContentId(): Int {
        return R.layout.activity_two_way_main
    }

    override fun onResume() {
        super.onResume()

        //系统编译时候会帮你生成ActivityMainBinding的类
        val activityMainBinding: ActivityTwoWayMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_two_way_main)

        //初始化data bean
        val text = ObservableField<String>("初始值")
        val defaultText = TextViewTwoWayContent(text)

        //将activity和 data绑定 Activity显示时会自动显示"初始值"
        activityMainBinding.textview = defaultText

        val editText = findViewById<EditText>(R.id.editText)
        editText.addTextChangedListener{
            println("text changed ${it.toString()}")
            text.set(it.toString())
        }


    }

}
