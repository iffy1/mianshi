package com.iffy.mianshi.pattern.IX迭代器

class ZaoCanMenu {

    var zaocan = ArrayList<String>()

    init {
        zaocan.add("鸡蛋")
        zaocan.add("面包")
        zaocan.add("牛奶")
    }

    fun getMenu():ArrayList<String>{
            return zaocan
    }

    fun getMenuB():Iterator<String>{
        return zaocan.iterator()
    }
}