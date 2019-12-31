@file:JvmName("MaoPaoShouXieKt")

package com.iffy.mianshi.algorithm.sort.practiceA
class MaoPaoShouXieB {

}
fun main() {
    var array = intArrayOf(2, 1, 5, 7, 8, 6, 9, 2, 5)

    for (i in 0..array.size - 1) {
        //println(i.javaClass)
        for (i in 0..array.size - 1) {
           // println(i.javaClass)
            if (i + 1 < array.size && array[i] > array[i + 1]) {
                var temp = array[i + 1]
                array[i + 1] = array[i]
                array[i] = temp
            }
        }
    }
    array.forEach {
        println(it)
    }
}