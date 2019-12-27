package com.iffy.mianshi.algorithm.sort.practiceA

fun main() {
    var array = intArrayOf(1, 3, 43, 546, 67, 8, 8, 4, 4, 5)
    for (i in 0..array.size - 2) {
        var j = i
        while (j >= 0) {
            if (array[j + 1] <= array[j]) {
                var temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp
            }
            j--
        }
    }
    array.forEach {
        print("$it ,")
    }
}
