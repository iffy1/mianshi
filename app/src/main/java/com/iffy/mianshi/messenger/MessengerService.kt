package com.iffy.mianshi.messenger

import android.app.Service
import android.content.Intent
import android.os.*

class MessengerService : Service() {
    lateinit var cMessenger: Messenger
    var handler = Handler(object : Handler.Callback {
        override fun handleMessage(p0: Message): Boolean {
            //返回信息给caller
            cMessenger = p0.replyTo
            println("------大大爷来了 打${p0.arg1}")

            var msg = Message()
            msg.arg1 = 110
            cMessenger.send(msg)
            return true
        }
    })
    var messenger = Messenger(handler)


    override fun onBind(p0: Intent?): IBinder? {
        return messenger.binder
    }
}