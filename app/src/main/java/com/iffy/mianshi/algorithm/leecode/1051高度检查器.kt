package com.iffy.mianshi.algorithm.leecode


fun main() {
    heightChecker(intArrayOf(1, 1, 4, 2, 1, 3))
}


fun heightChecker(heights: IntArray): Int {
    var h = heights.clone()
    heights.forEach {
        for (i in heights.indices) {
            if ((i < heights.size - 1) && (heights[i] > heights[i + 1])) {
                var temp = heights[i]
                heights[i] = heights[i + 1]
                heights[i + 1] = temp
            }
        }
    }
    heights.forEach(::print)
    println()
    h.forEach(::print)
    println()
    var count = 0
    for (j in h.indices){
        if(heights[j] != h[j]) count++
    }
    println(count)
    return count

}