package com.iffy.mianshi.leecode

fun main() {
    var a = ListNode(4)
    var dataA = intArrayOf(1, 8, 4, 5)
    var tempa = a
    dataA.forEach {
        tempa.next = ListNode(it)
        tempa = tempa.next as ListNode
    }

    var b = ListNode(5)
    var dataB = intArrayOf(0, 1, 8, 4, 5)
    var tempb = b
    dataB.forEach {
        tempb.next = ListNode(it)
        tempb = tempb.next as ListNode
    }

    var result = getIntersectionNode(a, b)

    if (result != null) {
        println()
        prinListNode(result)
    }


}


fun getIntersectionNode(headA: ListNode, headB: ListNode): ListNode? {
    //翻转两个链表后 从后往前比较两个链表
    prinListNode(headA)
    println()
    prinListNode(headB)
    println()
    var a = revertListNoe(headA)
    var b = revertListNoe(headB)

    if (a == null || b == null) {
        return null
    }
    prinListNode(a)
    println()
    prinListNode(b)
    lateinit var last: ListNode
    while (a!!.value == b!!.value) {
        last = a
        a = a.next
        b = b.next
    }
    return last
}

fun prinListNode(head: ListNode) {
    var temp: ListNode? = head
    while (temp != null) {
        System.out.print(temp.value)
        temp = temp.next
    }
}

fun revertListNoe(head: ListNode): ListNode? {
    var temp: ListNode? = head
    var pre: ListNode? = null
    var next: ListNode? = null
    while (temp != null) {
        next = temp.next
        temp.next = pre
        pre = temp
        temp = next
    }
    return pre
}
