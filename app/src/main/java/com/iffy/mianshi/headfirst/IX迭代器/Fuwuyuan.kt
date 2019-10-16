package com.iffy.mianshi.headfirst.IX迭代器

class Fuwuyuan {
    var zao = ZaoCanMenu().getMenu().iterator()
    var zaob = ZaoCanMenu().getMenuB()
    var wan = WanCanMenu().getMenu().iterator()
    var wanb = WanCanMenu().getMenuB()
    fun printMenu() {
        zaob.forEach {
            println(it)
        }

        zao.forEach {
            println(it)
        }

        wan.forEach {
            println(it.value)
        }

        wanb.forEach {
            println(it.value)
        }

    }
}