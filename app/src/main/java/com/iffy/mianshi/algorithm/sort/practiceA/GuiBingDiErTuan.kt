package com.iffy.mianshi.algorithm.sort.practiceA

class GuiBingDiErTuan {}

var data1 = intArrayOf(2, 3, 4, 5, 76, 1, 7, 8, 9)
fun main() {
    printDataE()
    guiBingSortE(0, data1.size - 1)
    printDataE()
}
fun printDataE() {
    data1.forEach {
        print("$it ,")
    }
    println()
}
fun guiBingSortE(start: Int, end: Int) {
    if (start >= end) {
        return
    }
    var mid = (start + end) / 2 //8个数的数组的话 7/2 =3是第四个数
    println("@@@@@@@@@@@@@@@@@@@@@ $start + $end /2 = $mid")
    guiBingSortE(start, mid) //0-4前四个数
    guiBingSortE(mid + 1, end)//4-8后四个数
    merge(start, mid, end)
}

fun merge(start: Int, mid: Int, end: Int) {
    println("start:$start ,min:$mid ,end:$end")
    var tempArray = IntArray(end - start + 1)
    var tempIndex = 0
    var p1 = start
    var p2 = mid + 1//+1
    while (p1 <= mid && p2 <= end) { //<=
        if (data1[p1] >= data1[p2]) {
            tempArray[tempIndex] = data1[p2]
            p2++
            tempIndex++
        } else {
            tempArray[tempIndex] = data1[p1]
            p1++
            tempIndex++
        }
    }

    while (p1 <= mid) {//<=
        tempArray[tempIndex] = data1[p1]
        p1++
        tempIndex++
    }

    while (p2 <= end) {//<=
        tempArray[tempIndex] = data1[p2]
        p2++
        tempIndex++
    }

    var ind = start
    tempArray.forEach {
        data1[ind] = it
        ind++
    }
}