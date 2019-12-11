package com.iffy.mianshi.lrucache

import android.util.Log
import androidx.collection.LruCache


val MAX_SIZE = 50
fun main() {
    startRun()
}

fun startRun() {
    val sample = LruCacheSample()
    println("LruCacheSample Start Put Object1, size= ${sample.size()}")

    sample.put("Object1", Holder("Object1", 10))
    println("LruCacheSample Start Put Object2, size=${sample.size()}")

    sample.put("Object2", Holder("Object2", 200))//这货太大了 放不进去，因为size只有50

    println("sample的大小为： ${sample.size()}")
    sample.snapshot().forEach { t, u ->
        println(t)
        println(u)
    }
    println("LruCacheSample Start Put Object3, size=${sample.size()}")

    sample.put("Object3", Holder("Object3", 30))
    println("LruCacheSample Start Put Object4, size=${sample.size()}")

    sample.put("Object4", Holder("Object4", 10))
    println("LruCacheSample Start Put Object4, size=${sample.size()}")
}

class LruCacheSample : androidx.collection.LruCache<String, Holder>(MAX_SIZE) {

    override fun sizeOf(key: String, value: Holder): Int {
        return value.size
    }

    override fun entryRemoved(evicted: Boolean, key: String, oldValue: Holder, newValue: Holder?) {
        super.entryRemoved(evicted, key, oldValue, newValue)

        if (oldValue != null) {
            println("LruCacheSample remove=  ${oldValue.name}")
        }
        if (newValue != null) {
            println("LruCacheSample add=  ${newValue.name}")
        }
    }
}

class Holder(val name: String, val size: Int)