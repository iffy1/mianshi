package com.iffy.mianshi.messenger

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity

class MessengerActivity : AppCompatActivity() {

    var handler = Handler(object : Handler.Callback {
        override fun handleMessage(p0: Message): Boolean {
            println("-----你大大没来，打${p0.arg1}")
            return true
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var i = Intent()
        i.setClass(this, MessengerService::class.java)

        bindService(i, object : ServiceConnection {
            override fun onServiceDisconnected(p0: ComponentName?) {
            }

            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                var msg = Message()
                msg.arg1 = 119
                msg.replyTo = Messenger(handler)
                Messenger(p1).send(msg)
            }
        }, Service.BIND_AUTO_CREATE)
    }


}