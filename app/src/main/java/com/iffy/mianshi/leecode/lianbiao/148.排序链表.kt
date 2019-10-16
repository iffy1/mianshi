package com.iffy.mianshi.leecode.lianbiao

import com.iffy.mianshi.leecode.ListNode
var result = ListNode(-1)

fun main() {
    var ln = ListNode(4)
    var temp = ln
    var a = arrayOf(2, 1, 3)
    a.forEach {
        temp.next = ListNode(it)
        temp = temp.next as ListNode
    }

    var result = sortList(temp)

}

fun sortList(head: ListNode?): ListNode? {
    var temp = head

    while (temp != null) {
        insert(temp)
        temp = temp.next
    }

    return result.next

}

fun insert(point: ListNode?) {
    var tem = result
    println(tem)
    while (tem.next!!.value < point!!.value) {
        tem = tem.next as ListNode
        if (tem == null || tem.next == null || point == null) {
            break
        }
        println(tem)

    }
    point.next = tem.next
    tem.next = point

}