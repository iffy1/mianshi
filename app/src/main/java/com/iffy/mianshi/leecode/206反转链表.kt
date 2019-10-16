package com.iffy.mianshi.leecode

import java.util.ArrayList

//示例:
//
//输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL

class ListNode(v: Int) {
    var next: ListNode? = null //每个节点指向下一个节点的连接
    var value: Int = v
}

fun main() {
    var head = ListNode(1)
    initData(head)//初始链表
    prin(head)
    println("")
    println("------------------------")

    //方法1 转换为数组然后反向
    println("------------转换为数组然后反向------------")
    var result = bianLiLianBiaoBaoCunDaoShuzu(head)
    result.reverse()
    println(result)
    //var resultListNode = ListNode(result[0])
    var aaaaaa = resultFromArray(result)
    prin(aaaaaa)
    println("")
    println("------------转换为数组然后反向------------")
    //转换为数组然后反向


    //方法2 每个节点放到数组中 然后反向取出
    var resultB: ListNode? = head
    var ndArray = arrayListOf<ListNode>()
    while (resultB != null) {
        resultB.next = null
        ndArray.add(resultB)
        resultB = resultB.next
    }
    ndArray.reverse()
    resultB = null
    var i = 0


    var temp: ListNode? = null
    ndArray.forEach {
        if (resultB == null) {
            resultB = it
            temp = resultB
            return@forEach
        }
        resultB!!.next = it
        resultB = resultB!!.next
        println(i++)
    }
    prin(temp)

    //方法2 每个节点放到数组中 然后反向取出

    //PQR 链表 切断
    println("")
    println("-----------PQR 链表 切断-------------")

    println("-----------PQR 链表 切断-------------")
    //PQR 链表 切断


}

fun resultFromArray(a: ArrayList<Int>): ListNode? {
    var temp: ListNode? = null
    var head: ListNode? = null
    a.forEach {
        if (head == null) {
            head = ListNode(it)
            temp = head
        } else {
            temp!!.next = ListNode(it)
            temp = temp!!.next
        }
    }
    return head
}

fun bianLiLianBiaoBaoCunDaoShuzu(l: ListNode): ArrayList<Int> {
    var result = ArrayList<Int>()
    var temp: ListNode? = l
    while (temp != null) {
        result.add(temp.value)
        temp = temp.next
    }
    return result
}


fun initData(l: ListNode?) {
    l?.next = ListNode(l!!.value + 1)
    if (l!!.value + 1 < 5) {
        initData(l?.next)
    }
}

fun prin(list: ListNode?) {
    print("${list?.value},")
    if (list != null) {
        prin(list.next)
    }
}