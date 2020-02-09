package com.iffy.module_mvvm


import android.content.Intent
import android.view.View
import com.iffy.module_base.BaseActivity
import com.iffy.module_mvvm.mvvm.ui.MVVMActivity
import com.iffy.module_mvvm.oneWayDataBinding.view.MainOneWayActivity
import com.iffy.module_mvvm.twoWayDataBinding.view.MainTwoWayActivity


class MainActivity : BaseActivity() {
    override fun getContentId(): Int {
        return R.layout.activity_main
    }

    override fun onStop() {
        super.onStop()
        println("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }

    fun goToOneWay(v: View) {
        val intent = Intent()
        intent.setClass(this, MainOneWayActivity::class.java)
        startActivity(intent)
    }

    fun goToTwoWay(v: View) {
        val intent = Intent()
        intent.setClass(this, MainTwoWayActivity::class.java)
        startActivity(intent)

    }

    fun goToMVVMActivity(v:View){
        val intent = Intent()
        intent.setClass(this,MVVMActivity::class.java)
        startActivity(intent)
    }

}
