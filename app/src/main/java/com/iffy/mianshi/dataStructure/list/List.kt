package com.iffy.mianshi.dataStructure.list

import java.util.*
import kotlin.collections.ArrayList


//Collection
//├List
//│├LinkedList
//│├ArrayList
//│└Vector
//│　└Stack
//└Set

//Map
//├Hashtable
//├HashMap
//└WeakHashMap

//https://blog.csdn.net/qq_27093465/article/details/50973442
//面试官问你的时候，你怼他一句，工程里面用的都是ArrayList，剩下的两个，我全局搜索就没见到过，问着有意思吗。

//动图
//https://www.jikewenku.com/9614.html
var linkedList =
    LinkedList<Int>()//Searched +full:linkedlist +type:java (Results 1 - 25 of 918)
var arrayList =
    ArrayList<Int>()//Searched +full:arraylist +type:java (Results 1 - 25 of 11543)

var vectotorList = Vector<Int>()//Searched +full:vector +type:java (Results 1 - 25 of 970)

fun main() {

    var size = 10
   println(size shr 1)

    inserFromHead()
    insertFromTail()
}

fun inserFromHead() {
    linkedList.clear()
    arrayList.clear()
    var time = System.currentTimeMillis()
    for (i in 0..100000) {
        linkedList.add(0, i)//linkBefore(element, node(index)); node(index)里面判断头尾距离 然后从头或者从尾开始查找node
    }
    println("头部插入100000个数字到linkedList耗时${System.currentTimeMillis() - time} 毫秒")

    time = System.currentTimeMillis()
    for (i in 0..100000) {
        arrayList.add(0, i) //     System.arraycopy(elementData, index, elementData, index + 1, size - index);
    }
    println("头部插入100000个数字到ArrayList耗时${System.currentTimeMillis() - time} 毫秒")

    time = System.currentTimeMillis()
    arrayList.get(75000)
    println("ArrayList找第75000条数据耗时${System.currentTimeMillis() - time} 毫秒")

    time = System.currentTimeMillis()
    linkedList.get(75000)
    println("LinkedList找第75000条数据耗时${System.currentTimeMillis() - time} 毫秒")

}

fun insertFromTail() {
    linkedList.clear()
    arrayList.clear()
    var time = System.currentTimeMillis()
    for (i in 0..100000) {
        linkedList.add(i)
    }
    println("尾部插入100000个数字到linkedList耗时${System.currentTimeMillis() - time} 毫秒")

    time = System.currentTimeMillis()
    for (i in 0..100000) {
        arrayList.add(i)
    }
    println("尾部插入100000个数字到ArrayList耗时${System.currentTimeMillis() - time} 毫秒")


}