package com.iffy.mianshi.dataStructure.list

class MHashMap {


}
fun main(){
    var hm = HashMap<Int,String>()
    hm.put(1,"一")
    hm.put(100,"一百")
    hm.put(1000,"一千")


    hm.forEach { t, u ->
        println("$t ---- $u")
    }
}