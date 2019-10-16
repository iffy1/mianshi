package com.iffy.mianshi.headfirst.IX迭代器

class WanCanMenu {
    var wancan = HashMap<String,String>()
    init {
        wancan.put("A","大米饭")
        wancan.put("B","大米粥")
        wancan.put("C","小米粥")
    }
    fun getMenu():HashMap<String,String>{
        return wancan
    }

    fun getMenuB():Iterator<MutableMap.MutableEntry<String, String>>{
        return wancan.iterator()
    }


}