package com.iffy.mianshi.algorithm.sort

fun main() {
    //popSortFirst()
    //popSortSecond()
    //zijixiangde()
    popSortThird()
}

fun popSortThird() {
    dataC.forEach {
        print("$it,")
    }
    println()
    var count = 0
    //记录最后一次交换的位置
    var lastExchangeIndex = 0
    // 无序数列的边界，每次比较只需要比到这里为止
    var sortBorder = dataC.size - 1
    for (i in dataC.indices) {
        //有序标记，每一轮的初始是true
        var isSorted = true
        for (j in 0 until sortBorder) {
            if (dataC[j] > dataC[j + 1]) {
                val tmp = dataC[j]
                dataC[j] = dataC[j + 1]
                dataC[j + 1] = tmp
                //有元素交换，所以不是有序，标记变为false
                isSorted = false
                //把无序数列的边界更新为最后一次交换元素的位置
                lastExchangeIndex = j
            }
            count++
            dataC.forEach {
                print("$it,")
            }
            println()
        }
        sortBorder = lastExchangeIndex
        if (isSorted) {
            break
        }
    }

    println("漫画算法.popSortThird run $count times")
    dataC.forEach {
        print("$it,")
    }
    println()
}

//移动后指针移到最前端从0开始冒泡
fun zijixiangde() {
    dataD.forEach { print("$it,") }
    println()
    var count = 0
    var k = 1
    while (k < dataD.size) {
        if (k != 0 && (dataD[k - 1] > dataD[k])) {
            var temp = dataD[k - 1]
            dataD[k - 1] = dataD[k]
            dataD[k] = temp
            k = 1
        } else {
            k++
        }
        count++
    }
    println("漫画算法.zijixiangde run $count times")
    dataD.forEach { print("$it,") }
    println()
}

fun popSortSecond() {
    dataB.forEach {
        print("$it,")
    }
    println()
    var count = 0
    for (i in dataB.indices) {
        var sorted = false
        for (j in dataB.indices) {
            if (j >= 1 && (dataB[j - 1] > dataB[j])) {
                var temp = dataB[j - 1]
                dataB[j - 1] = dataB[j]
                dataB[j] = temp
                sorted = true
            }
            count++
            dataB.forEach {
                print("$it,")
            }
            println()
        }
        if (!sorted) break
    }
    println("漫画算法.popSortSecond run $count times")
    dataB.forEach {
        print("$it,")
    }
    println()
}


fun popSortFirst() {
    dataA.forEach {
        print("$it,")
    }
    println()
    var count = 0
    for (i in dataA.indices) {
        for (j in dataA.indices) {
            if (j >= 1 && (dataA[j - 1] > dataA[j])) {
                var temp = dataA[j - 1]
                dataA[j - 1] = dataA[j]
                dataA[j] = temp
            }
            count++

            dataA.forEach {
                print("$it,")
            }
            println()
        }
    }
    println("漫画算法.popSortFirst run $count times")
    dataA.forEach {
        print("$it,")
    }
    println()
}