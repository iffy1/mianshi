package com.iffy.mianshi.algorithm.sort.practiceA

import org.w3c.dom.NodeList
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Node {
    var next: Node? = null
    var value: Int = 0
}

fun main() {
    var head: Node? = Node()
    var temp: Node? = head
    for (i in 1..10) {
        var node = Node()
        node.value = i
        temp?.next = node
        temp = temp?.next
    }
    revertList(head)

    //var result:Node? = revertNode(head)

    val arrayList = ArrayList<Int>()

    var array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)

    val iit = array.iterator()
    arrayList.listIterator()
    var ii = arrayList.iterator()


    val linkedList = LinkedList<Int>()
    linkedList.listIterator()
    linkedList.iterator()

    val hashMap = HashMap<Int, Int>()
    var it = hashMap.iterator()
    while (it.hasNext()){
       var next = it.next()
        next.key
        next.value
    }

    var itb = hashMap.entries.iterator()
    while (itb.hasNext()) {
        var next = itb.next()
        next.value
        next.key
    }


}

fun revertList(head: Node?): Node? {
    var result: Node? = null
    var temp: Node? = head
    while (temp != null) {
        var p = temp.next
        temp.next = result
        result = temp
        temp = p
    }
    return result
}

fun revertNode(head: Node?): Node? {
    var result: Node? = null
    var temp = head
    while (temp != null) {
        var n = temp.next
        temp.next = result
        result = temp
        temp = n
    }

    while (result != null) {
        println(result.value)
        result = result?.next
    }

    return result
}


