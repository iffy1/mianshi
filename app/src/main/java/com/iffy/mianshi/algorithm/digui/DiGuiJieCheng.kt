package com.iffy.mianshi.algorithm.digui


//使用递龟计算 阶乘
// f(n)*f(n-1)*f(n-2)

/**重要的事情说三遍
1.找到结束条件 n<=1
2.找到等式关系 f(n) = n * f(n-1) */


fun main() {
    println(ff(4))
}

fun ff(n: Int): Int {
    if (n <= 1) {//退出条件
        return 1
    }
    return ff(n - 1) * n//等式关系
}



