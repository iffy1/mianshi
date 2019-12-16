package com.iffy.mianshi.algorithm.sort

class ShellSort {
    companion object {
        fun showData() {
            dataA.forEach {
                print("$it,")
            }
            println()
        }
    }

}

//1959年Shell发明，第一个突破O(n2)的排序算法，是简单插入排序的改进版。
//它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
/**有点难啊**/
fun main() {
    solutionIffy()
    //solutionB()
}

fun solutionB() {
    var exchangeconter = 0
    var loopCounter = 0
    var gap = dataA.size / 2
    while (gap > 0) {
        for (i in gap until dataA.size) {
            val temp = dataA[i]
            var j = i

            //插入排序
            while (j >= gap && dataA[j - gap] > temp) {
                dataA[j] = dataA[j - gap]
                j -= gap
                exchangeconter++
                println("temp :$temp")
            }

            loopCounter++
            dataA[j] = temp
            ShellSort.showData()
        }
        gap /= 2
    }
    ShellSort.showData()
    println("一共交换了 $exchangeconter 次,循环了 $loopCounter 次")
}

fun solutionIffy() {
    ShellSort.showData()
    var exchangeconter = 0
    var loopCounter = 0
    var gap = dataA.size / 2
    println("gap:$gap")
    while (gap > 0) {

        for (j in 0..dataA.size - gap) {
            var i = j
            //println("比较-----$i <> ${i+gap}")
            //这部分是“简单插入排序” 从后往前比较， 后面大则交换
            while (i >= gap && dataA[i] < dataA[i - gap]) {
                var value = dataA[i]
                dataA[i] = dataA[i - gap]
                dataA[i - gap] = value

                println("交换-----$i <> ${i + gap}")
                exchangeconter++
                i -= gap
            }

            ShellSort.showData()
            loopCounter++
        }

        gap = gap / 2
        println("gap:$gap")
    }
    ShellSort.showData()
    println("一共交换了 $exchangeconter 次,循环了 $loopCounter 次")
}

