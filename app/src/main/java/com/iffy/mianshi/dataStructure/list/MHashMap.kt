package com.iffy.mianshi.dataStructure.list

class MHashMap {


}
fun main(){
    var hmtest = HashMap<String,String>(10)
    println("hmsize: ${hmtest.size}")


    var hm = HashMap<Int,String>()
    hm.put(1,"一")
    hm.put(100,"一百")
    hm.put(1000,"一千")


    hm.forEach { t, u ->
        println("$t ---- $u")
    }


    println(6 and 100) //4
    println(6 and 10) //2
    println(6 and 5) //4
    println(6 and 2) //2
}