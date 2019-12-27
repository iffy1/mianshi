package com.iffy.mianshi.binder.app2system

import android.bluetooth.BluetoothManager
import android.content.Context
import android.os.Binder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Bluetooth :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var btService = getSystemService(Context.BLUETOOTH_SERVICE)
        btService as BluetoothManager




    }

    class a: Binder() {


    }
}