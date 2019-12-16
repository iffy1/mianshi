package com.iffy.mianshi.algorithm.sort

//插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。
// 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描(交换)
//跟冒泡也差不多

//拿到排好的序列的后面一位 跟前面的挨个交换 直到比前面数字大
var counterb = 0

fun main() {
    showData()

    insertSort()

    println("${dataA.size}个数 排序，一共循环了$counterb 次")
}

//插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，
//需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
fun insertSort() {
    for (j in 0..dataA.lastIndex - 1) {
        var index = j
        while (index >= 0 && dataA[index + 1] < dataA[index]) {
            val temp = dataA[index]
            dataA[index] = dataA[index + 1]
            dataA[index + 1] = temp
            index--//从后往前比较 所以是--
            showData()
            counterb++
        }
    }
}

fun showData() {
    dataA.forEach {
        print("$it,")
    }
    println()
}



