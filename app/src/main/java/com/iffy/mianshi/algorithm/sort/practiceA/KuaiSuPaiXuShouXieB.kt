package com.iffy.mianshi.algorithm.sort.practiceA

class KuaiSuPaiXuShouXieB {
}

var data = intArrayOf(2, 8, 7, 6, 54, 3, 3, 7, 8, 9)
fun main() {
    printData()
    sortB(0, data.size - 1)
    printData()
}

fun sortB(left: Int, right: Int) {
    if (left > right) {
        return
    }

    var linJieZhi = findLinJiezhi(left, right)
    sortB(left, linJieZhi - 1)
    sortB(linJieZhi + 1, right)
}

fun findLinJiezhi(left: Int, right: Int): Int {
    var linJieZhi = data[left]
    var start = left
    var end = right

    while (start != end) {
        while (data[end] >= linJieZhi && start < end) {
            end--
        }

        while (data[start] <= linJieZhi && start < end) {
            start++
        }

        if(start < end)
        switch(start, end)
    }
    printData()
    switch(start, left)
    printData()
    return start
}

fun switch(a: Int, b: Int) {
    var temp = data[a]
    data[a] = data[b]
    data[b] = temp
}

fun printData() {
    data.forEach {
        print("$it ,")
    }
    println()
}