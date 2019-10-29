package com.iffy.mianshi.binder

import android.os.Binder
import android.os.IBinder
import android.os.IInterface
import android.os.Parcel

class MyBinder : Binder, IInterface {

    constructor(){
        attachInterface(this, "com.sonymobile.hookservice.HookContactService")
    }
    override fun asBinder(): IBinder {
        return this
    }

    override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
        return super.onTransact(code, data, reply, flags)
    }

}