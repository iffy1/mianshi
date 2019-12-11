package com.iffy.mianshi.firebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics

//开发（Develop）
//Cloud Messaging 云消息传递
//Authentication 身份验证
//Realtime Database 实时数据库
//Storage 存储
//Hosting 托管
//Remote Config 远程配置
//Test Lab 测试实验室
//Crash Reporting 崩溃报告

//增长（Grow）
//Notifications 通知
//App Indexing 搜索
//Dynamic Links 动态链接
//Invites 邀请，分享
//AdWords 广告

//获利（Earn）
//通过向全球受众展示引人入胜的广告赚钱。
//通过使用谷歌的移动广告平台AdMob来获取利益。


class MyFireBase : AppCompatActivity() {
    lateinit var fb: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fb = FirebaseAnalytics.getInstance(this)

        var b = Bundle()
        b.putString(FirebaseAnalytics.Param.ITEM_ID, "idA")
        b.putString(FirebaseAnalytics.Param.ITEM_NAME, "iffy")
        fb.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, b)
    }
}