package com.iffy.mianshi.algorithm.sort.practiceA


class GuiBingPaiXuTest {
}

var guiBingDataE = intArrayOf(3, 4, 5, 21, 5, 6, 87, 99, 7, 8)

fun main() {


    //printGuiBingData()
    guiBingSort(0, guiBingDataE.size - 1)
    printGuiBingDataC()
}

fun guiBingSort(start: Int, end: Int) {
    if (start >= end) {
        return
    }
    println("start is $start, end is $end")
    var mid = (start + end) / 2

    guiBingSort(start, mid)
    guiBingSort(mid + 1, end)
    guiBingMerge(start, mid, end)
}

fun guiBingMerge(left: Int, mid: Int, right: Int) {//左中右为原始数组的坐标
    println("11111111111111111111111111111 ${guiBingDataE.size}")
    val tempArray = IntArray(right - left + 1)//注意临时数组的大小 必须设置数组的大小
    var tempIndex = 0

    var l = left
    var m = mid + 1

    while (l <= mid && m <= right) {
        println("l:$l,m:$m , r:$right , tempArraysize: ${tempArray.size}")
        if (guiBingDataE[l] < guiBingDataE[m]) {
            tempArray[tempIndex] = guiBingDataE[l]
            l++
            tempIndex++
        } else {
            tempArray[tempIndex] = guiBingDataE[m]
            m++
            tempIndex++
        }

    }
    println("++++++++++++++++++++++++++++++++++++")
    printGuiBingDataC()
    println("++++++++++++++++++++++++++++++++++++")


    while (l <= mid) {
        tempArray[tempIndex] = guiBingDataE[l]
        l++
        tempIndex++
    }

    while (m <= right) {
        tempArray[tempIndex] = guiBingDataE[m]
        m++
        tempIndex++
    }


    var ind = left//排列后从原始数组的左起点放入
    tempArray.forEach {
        guiBingDataE[ind] = it
        ind++
        print("$it ,")
    }
    println("TEMP|||||")


}

fun printGuiBingDataC() {
    guiBingDataE.forEach {
        print("$it ,")
    }
    println()
}