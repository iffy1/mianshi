package com.iffy.mianshi.hashmapSpareArray

import android.util.Log
import java.lang.Runnable

/**
 *     author : iffy
 *     time   : 2020/03/11
 */


    fun main(){
        //for (i in 0..10){
            Thread(RunnablePut).start()
       // }

        //for (i in 0..10){
            Thread(RunnableGet).start()
        //}

    }

    val map = HashMap<Int,Int>(15)

    val RunnablePut = Runnable {
        for(i in 0..5000){
            Log.e("iffy","${Thread.currentThread().name} put $i")
            map.put(i,i)
        }
    }

    val RunnableGet = Runnable {
        for(i in 0..5000){
            Log.e("iffy","${Thread.currentThread().name} get $i")
            map.get(i)
        }
    }





