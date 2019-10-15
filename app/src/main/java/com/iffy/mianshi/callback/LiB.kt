package com.iffy.mianshi.callback

open class LiB {

   open fun executeMessage(wangb: WangB, num: Int) {
        println("小王的问题是: $num")

        //模拟办事
        var count = 0
        for (i in 0 until num) {
            if (i > 0 && i % 2 == 0) {
                count++
            }
        }

        val reslut = "答案是$count"

        //得到答案，告诉小王
       wangb.solve(reslut)
    }
}

