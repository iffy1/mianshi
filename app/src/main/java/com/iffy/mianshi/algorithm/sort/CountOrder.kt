package com.iffy.mianshi.algorithm.sort

fun main() {
    countSort(dataA)
}

fun countSort(data: IntArray) {
    var min = data.min()
    var max = data.max()
    var gap = max!! - min!!
    var list = arrayListOf<Int>()

    for (i in 1..gap + 1) {
        list.add(0)
    }
    println("list.size:$list")

    dataA.forEach {
        println(it)
        list[it - min]++
        println("list.size:$list")
    }

    println("result:")
    var ii = 0
    list.forEach {
        for (i in 1..it) {
            print("${ii + min},")
        }
        ii++
    }
}