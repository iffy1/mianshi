package com.iffy.mianshi.binder.app2system

import android.os.IBinder

interface IServiceMMM {
   fun addService(name:String)
   fun getService(name:String): IBinder
}