package com.iffy.mianshi.bindser

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


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