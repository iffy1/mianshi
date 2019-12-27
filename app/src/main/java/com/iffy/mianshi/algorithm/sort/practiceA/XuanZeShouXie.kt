package com.iffy.mianshi.algorithm.sort.practiceA

fun main() {
    var array = intArrayOf(9, 87, 6, 5, 4, 3, 2, 21, 1)
    for (i in 0..array.size - 1) {
        var minIndex = i
        for (j in i + 1..array.size - 1) {
            if (array[j] < array[minIndex]) {
                minIndex = j
            }
        }
        var temp = array[i]
        array[i] = array[minIndex]
        array[minIndex] = temp
    }
    array.forEach {
        println("$it ,")
    }
}
