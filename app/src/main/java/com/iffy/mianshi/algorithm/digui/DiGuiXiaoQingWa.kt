package com.iffy.mianshi.algorithm.digui


//一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法

/**重要的事情说三遍
1.找到结束条件 f(1) == 1 , f(2) ==2
2.找到等式关系 一共n种走法的话 如果第一步走1级 还剩下 f(n-1)种走法，
如果第一步走2级还剩f(n-2)种走法，一共f(n-1)+f(n-2)种走法
所以 f(n) = f(n-1)+f(n-2)
 */


fun main() {
    println(fff(5))
}

fun fff(n: Int): Int {
    if (n == 2) {
        return 2
    }
    if (n == 1) {
        return 1
    }
    return fff(n - 1) + fff(n - 2)
}




