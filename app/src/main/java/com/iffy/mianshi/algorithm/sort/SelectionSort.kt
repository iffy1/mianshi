package com.iffy.mianshi.algorithm.sort



//选择排序 每次遍历找到最小的数字
var counter = 0

fun main() {
    showDataA()//排序前显示

    basic()

    println("${dataA.size}个数 排序，一共循环了$counter 次")
}


//表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，所以用到它的时候，数据规模越小越好。
// 唯一的好处可能就是不占用额外的内存空间了吧。理论上讲，选择排序可能也是平时排序一般人想到的最多的排序方法了吧。
fun basic() {
    for (j in dataA.indices) {
        var minInd = j
        for (i in j..dataA.size - 1) {//从j开始查找
            if (dataA[minInd] > dataA[i]) {
                minInd = i
            }
            counter++
        }
        //交换
        val temp = dataA[j]
        dataA[j] = dataA[minInd]
        dataA[minInd] = temp
        showDataA()
    }
}


fun showDataA() {
    dataA.forEach {
        print("$it,")
    }
    println()
}