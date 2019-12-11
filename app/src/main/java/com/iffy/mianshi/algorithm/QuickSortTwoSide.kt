package com.iffy.mianshi.algorithm

fun main() {
    //var data = intArrayOf(4, 7, 3, 5, 6, 2, 8, 4, 1)
    var data = intArrayOf(4, 7, 3, 5, 6, 2, 8, 1, 11)
    quickSortTwoSide(data, 0, data.size - 1)
    data.forEach(::print)
}


fun quickSortTwoSide(arry: IntArray, startIndex: Int, endIndex: Int) {
    println("start:$startIndex|end:$endIndex")
    if (startIndex >= endIndex) return
    var pivot = findPivotTwo(arry, startIndex, endIndex)
    quickSortTwoSide(arry, startIndex, pivot - 1)
    quickSortTwoSide(arry, pivot + 1, endIndex)
}

fun findPivotTwo(arry: IntArray, startIndex: Int, endIndex: Int): Int {
    var pivot = arry[startIndex]
    var left = startIndex
    var right = endIndex

    while (left < right) {
        while (arry[right] > pivot) {
            right--
        }

        while (arry[left] <= pivot && left < right) {
            left++
        }

        var temp = arry[left]
        arry[left] = arry[right]
        arry[right] = temp

    }
    arry.forEach(::print)
    println("")
    println("left is $left---------------")

    arry[startIndex] = arry[left]
    arry[left] = pivot
    println("left is $left")
    arry.forEach(::print)
    println("")
    return left
}
