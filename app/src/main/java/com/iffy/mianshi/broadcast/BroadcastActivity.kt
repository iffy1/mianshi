package com.iffy.mianshi.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class BroadcastActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var receiver = Myreceiver()
        var intentfilter = IntentFilter()
        intentfilter.addAction("woqu")
        this.registerReceiver(receiver, intentfilter)
        this.sendBroadcast(Intent("woqu"))
       // startActivity(Intent("aa"))


        //本地广播防止信息泄露
        val localIntentfilter = IntentFilter()
        localIntentfilter.addAction("woqulocal")
        LocalBroadcastManager.getInstance(this).registerReceiver(MyLocalreceiver(),localIntentfilter)
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent("woqulocal"))
        // this.unregisterReceiver(receiver)
    }

    inner class Myreceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            println("----我收到消息:${p1!!.action}")
        }
    }

    inner class MyLocalreceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            println("----我收到消息:${p1!!.action}")
        }
    }
}