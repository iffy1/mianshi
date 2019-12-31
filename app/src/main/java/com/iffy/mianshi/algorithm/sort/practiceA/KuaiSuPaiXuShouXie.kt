package com.iffy.mianshi.algorithm.sort.practiceA

class KuaiSuPaiXuShouXie {
}

var mArray = intArrayOf(5, 3, 4, 9, 8, 7, 6, 1, 4, 4, 2)
fun main() {
    printArray()
    sort(0, mArray.size - 1)
    printArray()
}

fun sort(startIndex: Int, endIndext: Int) {
    if (startIndex >= endIndext) {
        return
    }

    var pivot = getProvi(startIndex, endIndext)
    sort(startIndex, pivot - 1)
    sort(pivot + 1, endIndext)
}

fun getProvi(startIndex: Int, endIndext: Int): Int {
    var start = startIndex
    var end = endIndext
    var pivotValue = mArray[startIndex]
    while (start < end) {
        while (mArray[end] >= pivotValue && end > start) {
            end--
        }

        while (mArray[start] <= pivotValue && start < end) {
            start++
        }
        swap(start, end)
    }
    swap(start, startIndex)//交换临界值
    return start
}

fun swap(start: Int, end: Int) {
    var temp = mArray[start]
    mArray[start] = mArray[end]
    mArray[end] = temp
}

fun printArray() {
    mArray.forEach {
        print("$it ,")
    }
    println("")
}


