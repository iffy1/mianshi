package com.iffy.mianshi.algorithm.leecode.lianbiao

import com.iffy.mianshi.algorithm.leecode.ListNode

class Solution {

    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var count = 0
        var temp = head
        while (temp != null) {
            count++
            temp = temp.next
        }

        temp = head
        for (i in 0..(count - n + 1)) {
            temp = temp!!.next
        }

        temp!!.next = temp!!.next!!.next

        return head


    }

}