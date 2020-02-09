package com.iffy.module_base

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.iffy.module_base.utils.StatusBarUtil


//Base Activity的作用
//1、动态获取权限
//2、多种屏幕适配
//3、页面滑动返回
//4、封装toolbar
//5、获取数据库（数据库的初始化等在application里）
//6、注册，销毁Eventbus
//7、一键退出所有activity（我认为我的这种方法是最好的）

abstract class BaseActivity : AppCompatActivity() {
    val TAG = this.javaClass.simpleName

    companion object {
        fun getName(): String {
            return "BaseActivity"
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_base_main)
        val container = findViewById<ViewGroup>(R.id.container)
        View.inflate(this, getContentId(), container)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        //设置状态栏背景颜色 状态栏透明
        initStatusBar(drawerLayout)

        //设置ToolBar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        //申请权限
        val perms = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        //怎么知道NeverAskAgain，
        // 只是不弹权限框，但是会回调OnRequestPermissionsResult()
        requestPermissions(perms, 110)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var i = 0
        permissions.forEach {
            when (grantResults[i]) {
                PackageManager.PERMISSION_GRANTED -> println("$it is 赋予了权限")
                PackageManager.PERMISSION_DENIED -> {
                    println("$it is 没有权限")
                    Toast.makeText(BaseActivity@ this, "没有存储权限不能工作", Toast.LENGTH_SHORT).show()
                }
            }
            i++
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    protected fun initStatusBar(drawerLayout: DrawerLayout) {
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(
            this,
            drawerLayout, getColor(R.color.colorTheme)
        )
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

    abstract fun getContentId(): Int


}

