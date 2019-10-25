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

    inner class MyBinder : Binder() {
        fun getService():Beband{
         return this@Beband
        }

    }

}