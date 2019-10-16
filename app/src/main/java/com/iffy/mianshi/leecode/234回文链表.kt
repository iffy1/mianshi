package com.iffy.mianshi.leecode


fun main() {
    var ln = ListNode(1)
    var temp = ln
    var a = arrayOf(2, 3, 3, 2, 1)
    a.forEach {
        temp.next = ListNode(it)
        temp = temp.next as ListNode
    }

    println(isPalindrome(ln))


}

fun isPalindrome(head: ListNode?): Boolean {
    var list = ArrayList<ListNode?>()
    var temp = head
    while (temp != null && temp.next != null) {
        var a = temp.next as ListNode
        if (temp.value == a.value) {
            list.add(temp)
            temp = temp.next
            break
        }
        list.add(temp)
        temp = temp.next
    }

    var count = 0
    while (temp != null) {
        if (temp.value != list[list.size - count - 1]?.value) {
            return false
        }
        count++
        temp = temp.next
    }

    return true
}
