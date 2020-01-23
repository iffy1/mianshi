package com.iffy.mianshi.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyLocalBroadCastReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
       println("我是MyLocalBroadCastReceiver 我收到了消息：${intent?.action}")
    }
}