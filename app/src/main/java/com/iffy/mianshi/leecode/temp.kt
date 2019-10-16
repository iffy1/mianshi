package com.iffy.mianshi.leecode

fun main() {
    for (i in 1..100) {
        for (j in 99..199) {
            println(i)
            //if (i == 10)
        }


    }

    var data = intArrayOf(1, 2, 3, 4, 5)

    data.forEach aa@{
        if (it == 4) return@aa
    }

    val intArray = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    intArray.forEach here@{
        if (it == 3) return@here//指令跳转到lambda表达式标签here@处。
        println(it)          //继续下一个it = 4遍历循环
    }

}