package com.iffy.mianshi.algorithm.sort.practiceA


class GuiBingPaiXu {
}


fun main() {

    var guiBingData = intArrayOf(3, 4, 5, 21, 5, 6, 87, 99, 7, 8)
    //printGuiBingData()
    guiBingSort(guiBingData, 0, guiBingData.size - 1)
    printGuiBingData(guiBingData)
}

fun guiBingSort(guiBingData: IntArray, start: Int, end: Int) {
    if (start >= end) {
        return
    }
    println("start is $start, end is $end")
    var mid = (start + end) / 2

    guiBingSort(guiBingData, start, mid)
    guiBingSort(guiBingData, mid + 1, end)
    guiBingMerge(guiBingData, start, mid, end)
}

fun guiBingMerge(guiBingData: IntArray, left: Int, mid: Int, right: Int) {
    println("11111111111111111111111111111 ${guiBingData.size}")
    val tempArray = IntArray(right - left + 1)
    var tempIndex = 0

    var l = left
    var m = mid + 1

    while (l <= mid && m <= right) {
        println("l:$l,m:$m , r:$right , tempArraysize: ${tempArray.size}")
        if (guiBingData[l] < guiBingData[m]) {
            tempArray[tempIndex] = guiBingData[l]
            l++
            tempIndex++
        } else {
            tempArray[tempIndex] = guiBingData[m]
            m++
            tempIndex++
        }

    }
    println("++++++++++++++++++++++++++++++++++++")
    printGuiBingData(guiBingData)
    println("++++++++++++++++++++++++++++++++++++")


    while (l <= mid) {
        tempArray[tempIndex] = guiBingData[l]
        l++
        tempIndex++
    }

    while (m <= right) {
        tempArray[tempIndex] = guiBingData[m]
        m++
        tempIndex++
    }


    var ind = left
    tempArray.forEach {
        guiBingData[ind] = it
        ind++
        print("$it ,")
    }
    println("TEMP|||||")


}

fun printGuiBingData(data: IntArray) {
    data.forEach {
        print("$it ,")
    }
    println()
}