package com.iffy.mianshi.dataStructure.list

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.SparseArray
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.collections.forEach

class MSparceArray : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        //只有安卓SDK能用
        val spArray = SparseArray<String>()
        spArray.put(0, "hehe le")
        spArray.put(1, "wo qu")
        spArray.put(5, "");

        spArray.forEach { a: Int, b: Any ->
            println("a的值为$a ,b的值为$b ")
        }
    }
}



