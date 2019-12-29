package com.iffy.mianshi.algorithm.lrucache

fun main() {
    var data = MyLru(10)
    for (i in 0..99) {
        data.put(i, "data $i")
        println("insert $i")
    }

//    data.snapshot().forEach { t, u ->
//        println("剩下 $t  和 $u")
//    }
    data.snapshot().forEach {
        println("剩下 ${it.key}  和 ${it.value} ")

    }


}

class MyLru(maxSize: Int) : androidx.collection.LruCache<Int, String>(maxSize) {
    override fun entryRemoved(evicted: Boolean, key: Int, oldValue: String, newValue: String?) {
        super.entryRemoved(evicted, key, oldValue, newValue)
        println("entryRemoved key: $key")
    }
}