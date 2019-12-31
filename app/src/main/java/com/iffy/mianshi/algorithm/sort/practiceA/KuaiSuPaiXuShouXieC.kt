package com.iffy.mianshi.algorithm.sort.practiceA

class KuaiSuPaiXuShouXieC {
}

var dataC = intArrayOf(9, 8, 7, 6, 655, 4, 4, 3, 3, 33, 33, 2, 22, 21, 1)
fun main() {
    sortC(0, dataC.size - 1)
}

fun sortC(start: Int, end: Int) {
    if (start > end) {
        return
    }
    var pivot = findPivot(start, end)
    sortC(start, pivot - 1)
    sortC(pivot + 1, end)
}


fun findPivot(start: Int, end: Int): Int {
    var left = start
    var right = end
    var pivotValue = dataC[start]
    while (left < right) {
        while (dataC[right] >= pivotValue && left < right) {//一定要从右边先比较
            right--
        }
        while (dataC[left] <= pivotValue && left < right) {
            left++
        }
        switchB(right, left)
        println("left is $left, right is $right")
    }
    switchB(left, start)
    return left

}

fun switchB(a: Int, b: Int) {
    var temp = dataC[a]
    dataC[a] = dataC[b]
    dataC[b] = temp
}






