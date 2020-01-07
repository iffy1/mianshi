package com.iffy.mianshi.algorithm.sort.practiceA

import org.w3c.dom.NodeList
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class FindNode {
    var next: Node? = null
    var value: Int = 0
}

fun main(){

    var head:Node? = Node()
    var temp:Node? = head
    for (i in 1..10){
        var node = Node()
        node.value = i
        temp?.next = node
        temp = temp?.next
    }

    println(findK(head,5))


}

fun findK(head:Node?,k:Int):Int?{
    var p1:Node? =head
    var p2:Node? = head
    var index = 0
    while (p2!= null){
        p2=p2.next
        if (index>k){
            p1=p1?.next
        }
        index++
    }
    if(index<=k){
        return null
    }else{
        return p1!!.value
    }
}



