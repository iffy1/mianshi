package com.iffy.mianshi.algorithm.digui

//斐波那契数列的是这样一个数列：1、1、2、3、5、8、13、21、34....，
// 即第一项 f(1) = 1,第二项 f(2) = 1.....,第 n 项目为 f(n) = f(n-1) + f(n-2)。
// 求第 n 项的值是多少。

/**递归两要素
1.找到结束条件
2.找到相等关系*/


var count = 0
var STOPCONDITION = 9
fun main() {
    println(add(0, 1))
    println(f(STOPCONDITION))
}


fun f(n: Int): Int {
    //println(n)
    if (n <= 2) {//结束条件，如果n<2的话 f(n-2)中的n-2就是负数了
        return 1
    }
    return f(n - 1) + f(n - 2)//相等关系 f(n) =f(n-1)+f(n-2)
}


//这个自己写的不算递归
fun add(p: Int, n: Int): Int {
    println("n is $n")
    count++
    if (count == STOPCONDITION) {
        return n
    }
    return add(n, p + n)
}

