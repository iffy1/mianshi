package com.iffy.mianshi.algorithm.leecode.lianbiao

import java.util.*

fun main() {
    //初始化链表
    var temp = initList()
    println("打印初始链表")
    printNodeList(temp)

    //从尾部插入
    insertNodeListFromEnd(temp, 11)
    println("打印初始链表从尾部插入")
    printNodeList(temp)

    //按值的大小顺序插入 内存中排好顺序
    insertNodeListByValueOrder(temp, 5)
    insertNodeListByValueOrder(temp, 3)
    insertNodeListByValueOrder(temp, 7)
    insertNodeListByValueOrder(temp, 9)
    insertNodeListByValueOrder(temp, 2)
    println("打印按值的大小顺序插入")
    printNodeList(temp)

    //指定值插入
    insertNodeListByPosition(temp, 4, 999)
    println("打印指定数值插入")
    printNodeList(temp)

    //删除一个节点
    deleteNode(temp, NodeList(999))
    println("打印删除999后的链表")
    printNodeList(temp)

    //更新一个节点的值
    updateNode(temp, 6, 666)
    printNodeList(temp)
    updateNode(temp, 12, 666)
    updateNode(temp, 10, 666)
    printNodeList(temp)

    //数出节点个数
    println("节点的个数为${getNodeCount(temp)}")

    //找出倒数第K个节点
    println("倒数第6个节点是${findPenultimateKNode(temp, 6)!!.value}")
    println("倒数第11个节点是${findPenultimateKNode(temp, 11)!!.value}")
    println("倒数第15个节点是${findPenultimateKNode(temp, 15)!!.value}")

    println("准备头插法翻转链表")
    printNodeList(temp)
    //翻转链表 头插法
    printNodeList(revertNodeList(temp))

    //翻转链表 遍历法
    println("准备双指针遍历法翻转链表")
    printNodeList(temp)
    printNodeList(revertNodeListTraversal(temp))


    //从尾到头打印单链表 递归方法
    temp = initList()
    println("递归方法反向打印链表")
    printRevertRecursive(temp)
    println("")

    //从尾到头打印单链表 栈方法
    println("链表初始值：")
    printNodeList(temp)
    println("进栈方法反向打印链表")
    printRevertStack(temp)


    //将两个顺序链表合并， 合并后仍然有序
    var nlA: NodeList? = NodeList(-1)
    insertNodeListFromEnd(nlA, 1)
    insertNodeListFromEnd(nlA, 3)
    insertNodeListFromEnd(nlA, 4)
    insertNodeListFromEnd(nlA, 7)
    insertNodeListFromEnd(nlA, 9)

    var nlB: NodeList? = NodeList(-1)
    insertNodeListFromEnd(nlB, 2)
    insertNodeListFromEnd(nlB, 5)
    insertNodeListFromEnd(nlB, 6)
    insertNodeListFromEnd(nlB, 8)
    insertNodeListFromEnd(nlB, 10)

    println("将两个有序链表合并：")
    printNodeList(nlA)
    printNodeList(nlB)

    var result = NodeList(-1)
    var tempp = result

    //去掉头结点
    nlA = nlA!!.next
    nlB = nlB!!.next

    while (nlA!!.next != null || nlB!!.next != null) {
        if (nlA.value > nlB!!.value) {
            tempp.next = nlB
            nlB = nlB.next
            tempp = tempp.next as NodeList
        } else {
            tempp.next = nlA
            nlA = nlA.next
            tempp = tempp.next as NodeList
        }
    }
    printNodeList(result)


}

//栈反向打印
fun printRevertStack(head: NodeList?) {
    var stack = Stack<NodeList>()
    var temp = head
    while (temp != null) {
        stack.push(temp)
        //print("push ${temp.value}")
        temp = temp.next
    }
    while (!stack.empty()) {
        print("${stack.pop().value},")
    }
    println()
}

//递归从尾到头打印单链表
fun printRevertRecursive(head: NodeList?) {
    if (head!!.next != null) {
        printRevertRecursive(head!!.next)
    }
    print("${head.value},")
}

//链表翻转头插法
fun revertNodeList(head: NodeList?): NodeList? {
    var revertHead = NodeList(-6)
    var cur = head!!.next//保持头结点 并且将第二个节点传给遍历方法
    while (cur != null) {
        var next = cur!!.next//keep next数据
        var tail = revertHead.next//保持revert后面的链表
        revertHead.next = cur//新节点插入第二位
        cur.next = tail//将之前的尾巴接回新节点
        cur = next//节点遍历指针后移
    }
    head!!.next = revertHead.next//接回原来的头结点
    return head
}

//链表反向 遍历法
fun revertNodeListTraversal(head: NodeList?): NodeList? {
    var temp = head
    var next: NodeList?
    var pre: NodeList? = null
    while (temp != null) {
        //保持二节点
        next = temp.next
        //断开一二节点
        temp.next = pre
        //pre更新为新的temp节点
        pre = temp
        //下一轮节点更新为第一步保存的next节点
        temp = next
    }
    return pre
}

//找出倒数第k个节点
fun findPenultimateKNode(head: NodeList?, k: Int): NodeList? {
    var temp = head
    var total = 0
    while (temp != null) {
        temp = temp.next
        total++
    }
    if (total < k) {
        println("k:$k 超出节点范围:$total")
    }
    var tempb = head
    var i = 0
    while (i < (total - k)) {
        tempb = tempb!!.next
        i++
    }
    return tempb
}

//求单链表的节点个数
fun getNodeCount(head: NodeList?): Int {
    var result = 0
    if (head == null) {
        return result
    }
    var temp = head

    while (temp!!.next != null) {
        temp = temp.next
        result++
    }
    return result
}

fun updateNode(head: NodeList?, target: Int, value: Int) {
    var temp = head
    var found = false
    while (temp!!.next != null) {
        if (temp.value == target) {
            found = true
            break
        }
        temp = temp!!.next
    }
    if (found) {
        temp!!.value = value
        println("找到需要更新的节点:$target 并且更新为$value")
    } else {
        println("没有找到需要更新的节点:$target")
    }
}

fun deleteNode(head: NodeList?, node: NodeList) {
    var temp = head
    while (temp!!.next!!.value != node.value) {
        temp = temp.next
    }
    temp.next = temp.next!!.next
}

fun insertNodeListByValueOrder(head: NodeList?, i: Int) {
    var a = head
    while (a!!.next!!.value < i) {
        a = a.next
    }
    var newNode = NodeList(i)
    newNode.next = a.next
    a.next = newNode
}


fun insertNodeListByPosition(head: NodeList?, after: Int, value: Int) {
    var temp = head
    while (temp!!.value != after) {
        temp = temp.next
    }
    var newNode = NodeList(value)
    newNode.next = temp.next
    temp.next = newNode
}

fun insertNodeListFromEnd(head: NodeList?, value: Int) {
    var ttemp = head
    while (ttemp!!.next != null) {
        ttemp = ttemp.next
    }
    ttemp.next = NodeList(value)
    //println("insertNodeList head $head")
}

fun printNodeList(head: NodeList?) {
    var temp: NodeList? = head
    while (temp != null) {
        print("${temp.value},")
        temp = temp.next
    }
    println("")
}

fun initList(): NodeList? {
    var head = NodeList(-1)
    var temp: NodeList? = head
    for (i in 1..10) {
        if (i % 2 == 0) {
            temp!!.next = NodeList(i)
            temp = temp.next
        }
    }
    return head
}