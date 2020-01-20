package com.iffy.module_base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//Base Activity的作用
//1、动态获取权限
//2、多种屏幕适配
//3、页面滑动返回
//4、封装toolbar
//5、获取数据库（数据库的初始化等在application里）
//6、注册，销毁Eventbus
//7、一键退出所有activity（我认为我的这种方法是最好的）

open class BaseActivity : AppCompatActivity() {

    val TAG = this::javaClass.name

    companion object {
        fun getName(): String {
            return "BaseActivity"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }


}

