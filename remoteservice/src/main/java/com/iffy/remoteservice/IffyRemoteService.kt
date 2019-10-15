package com.iffy.remoteservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.io.FileDescriptor
import java.io.PrintWriter


class IffyRemoteService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return jiekou()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("iffy I am start !!!!!!!!!!!!!!!!!!!!!!!")
        return super.onStartCommand(intent, flags, startId)

    }

    class jiekou : IIFFYAidlInterface.Stub() {
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {

        }

        override fun getMSG(): String {
            return "你大大"
        }

    }


}