package com.iffy.mianshi.algorithm.sort.practiceA

fun main() {
    var array = intArrayOf(1, 34, 23, 65, 77, 65, 98, 2, 23, 11, 23, 1)
    var gap = array.size / 2
    while (gap > 0) {
        for (i in 0..array.size - 1) {
            var j = i + gap
            while (j - gap > 0) {
                if (j <= array.size - 1 && array[j] < array[j - gap]) {
                    var temp = array[j - gap]
                    array[j - gap] = array[j]
                    array[j] = temp
                }
                j = j - gap
            }
        }
        gap = gap / 2
    }
    array.forEach {
        print("$it ,")
    }
}
