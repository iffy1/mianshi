package com.iffy.mianshi.algorithm.sort

fun main() {
    //var data = intArrayOf(4, 7, 3, 5, 6, 2, 8, 4, 1)
    var data = intArrayOf(4, 7, 3, 5, 6, 2, 8, 1, 11)
    quickSortOneSide(data, 0, data.size - 1)
    data.forEach(::print)
}


fun quickSortOneSide(arry: IntArray, startIndex: Int, endIndex: Int) {
    println("start:$startIndex|end:$endIndex")
    if (startIndex >= endIndex) return
    var pivot = findPivotOne(arry, startIndex, endIndex)
    quickSortOneSide(arry, startIndex, pivot - 1)
    quickSortOneSide(arry, pivot + 1, endIndex)
}

fun findPivotOne(arry: IntArray, startIndex: Int, endIndex: Int): Int {
    var pivot = arry[startIndex]
    var left = startIndex
    var right = endIndex
    var mark = startIndex
    while (left <= right) {
        if (arry[left] < pivot) {
            mark++
            var temp = arry[left]
            arry[left] = arry[mark]
            arry[mark] = temp
        }
        left++
    }

    arry[startIndex] = arry[mark]
    arry[mark] = pivot
    println("mark is $mark")
    arry.forEach(::print)
    println("")
    return mark
}
