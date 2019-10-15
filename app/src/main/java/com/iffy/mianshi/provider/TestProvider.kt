package com.iffy.mianshi.provider

import android.content.ContentResolver
import android.content.ContentValues
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import java.net.URI


class TestProvider : AppCompatActivity() {
    init {
        var b = ContentValues()
        b.put("a", "a")
        this.contentResolver.insert(Uri.parse(""), b)


        var handler = Handler(object : Handler.Callback {
            override fun handleMessage(p0: Message): Boolean {
                return true
            }
        })
        var ob = MyObserver(handler)
        this.contentResolver.registerContentObserver(Uri.parse(""), true, ob)


    }


    class MyObserver(var han: Handler) : ContentObserver(han) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            var msg = han.obtainMessage()
            msg.arg1 = 0
            han.sendMessage(msg)
        }

        override fun onChange(selfChange: Boolean, uri: Uri?) {
            super.onChange(selfChange, uri)
        }
    }
}
