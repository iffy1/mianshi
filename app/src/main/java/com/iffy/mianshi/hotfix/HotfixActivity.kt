package com.iffy.mianshi.hotfix

import android.os.Bundle
import android.os.Environment
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R
import java.io.File
//不能工作
//在执行插件代码的过程中，系统可能会调用一些（公有的或私有的）接口获取应用的applicationId，
//然而插件从真正意义上来说并没有安装到设备上，如果插件的applicationId和宿主的applicationId不相同，
//系统获取到插件的applicationId是一个没有安装过的包名，系统就因此crash。
//为了避免出现上述情况，有两种方法：
//1. hook系统接口，需要兼容各种OEM系统以及Android各版本
//2. 插件的applicationId和宿主的applicationId保持一致(腾讯shadow的做法)
class HotfixActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotfix)

        var fix_btn = findViewById<Button>(R.id.fix_btn)
        fix_btn.setOnClickListener {
            //去/data/user/0/com.iffy.mianshi/files/odex 查找dex文件
            FixDexUtils.loadFixedDex(this)
        }
        var bug_btn = findViewById<Button>(R.id.getbug_btn)
        bug_btn.setOnClickListener {
            //BugClass.dairYouTryThis(this)
            BugClassJava.dairYouTryThis(this)
        }

    }
}