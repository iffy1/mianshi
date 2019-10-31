package com.iffy.mianshi.bindService

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

//MainActivity bind 这个Service
class Beband : Service() {
    var binder = MyBinder()

    override fun onBind(p0: Intent?): IBinder? {
        println("---------------------Service Banded")
        return binder
    }

    fun guess(): String {
        return "I am your big father"
    }
    //inner为内部类声明，可以访问外部类环境变量
    //如果没有inner的话，这个类默认为嵌套类（java中叫静态类）
    inner class MyBinder : Binder() {
        fun getService():Beband{
         return this@Beband
        }

    }

}