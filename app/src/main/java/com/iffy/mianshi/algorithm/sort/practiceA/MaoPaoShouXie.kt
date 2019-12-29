package com.iffy.mianshi.algorithm.sort.practiceA
class MaoPaoShouXie {
    fun main() {
        var array = intArrayOf(2, 44, 67, 8, 84, 1, 1, 3)
        for (k in 0..array.size - 1) {
            for (i in 0..array.size - 1) {
                if (i + 1 < array.size  && array[i] > array[i + 1]) {
                    var temp = array[i + 1]
                    array[i + 1] = array[i]
                    array[i] = temp
                }
            }
        }
        array.forEach {
            print("$it ,")
        }
    }
}

