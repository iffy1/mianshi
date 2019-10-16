package com.iffy.mianshi.leecode

fun main() {
    var a = intArrayOf(3, 0, 8, 4)
    var b = intArrayOf(2, 4, 5, 7)
    var c = intArrayOf(9, 2, 6, 3)
    var d = intArrayOf(0, 3, 1, 0)
    var dd = arrayOf(a, b, c, d)
    maxIncreaseKeepingSkyline(dd)
}

fun maxIncreaseKeepingSkyline(grid: Array<IntArray>): Int {
    var begin =0
    grid.forEach{it.forEach {begin+=it}}


    var aH = ArrayList<Int>()
    grid.forEach {
        var max = 0
        it.forEach { num ->
            if (num >= max) max = num
        }
        aH.add(max)
    }
    aH.forEach(::print)
    println()

    var aV = ArrayList<Int>()
    var index = 0
    while (index < grid.size) {
        var max = 0
        grid.forEach {
            if (it[index] > max) max = it[index]
        }
        aV.add(max)
        index++
    }
    aV.forEach(::print)
    println()


    var ind = 0
    grid.forEach {
        var index = 0
        it.forEach {  num ->
            if (num <= aH[ind]) it.set(index,aH[ind])
            index++
        }
        ind++
    }
    grid.forEach{it.forEach (::print)}
    println()



    index = 0
    while (index < grid.size) {
        grid.forEach {
            if (it[index] > aV[index]) it.set(index,aV[index])
        }
        index++
    }

    var result =0
    grid.forEach{it.forEach {result+=it}}

    println(result-begin)
    return result

}

