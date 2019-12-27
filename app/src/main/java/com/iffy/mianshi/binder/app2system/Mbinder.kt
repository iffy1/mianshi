package com.iffy.mianshi.binder.app2system

import android.os.Binder
import android.os.IBinder
import com.iffy.mianshi.binder.IPlus

class Mbinder: Binder(), IPlus {
    override fun add(a: Int, b: Int): Int {
        return 3
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun asBinder(): IBinder {
        return this
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}