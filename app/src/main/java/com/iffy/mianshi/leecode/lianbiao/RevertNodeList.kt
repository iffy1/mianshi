package com.iffy.mianshi.leecode.lianbiao


fun main() {
    var origini = initNode()
    printNodeList(origini)
    var result = revertNoteList(origini)
    printNodeList(result)

    println("准备头插法：")
    origini = initNode()
    printNodeList(origini)
    var headResult = revertHeadMethod(origini)
    printNodeList(headResult)
}

//头插法
fun revertHeadMethod(head: NodeList): NodeList? {
    var temp: NodeList? = head
    var newHead = NodeList(-1)
    while (temp != null) {
        //保存第二个节点
        var next = temp.next
        temp.next = newHead.next
        newHead.next = temp
        temp = next
    }
    return newHead
}

//双指针遍历法
fun revertNoteList(head: NodeList): NodeList? {
    var temp: NodeList? = head
    var pre: NodeList? = null
    var next: NodeList? = null
    while (temp != null) {
        next = temp.next
        temp.next = pre
        pre = temp
        temp = next
    }
    return pre
}

fun printNodeList(head: NodeList) {
    var temp: NodeList? = head
    while (temp != null) {
        print("${temp.value},")
        temp = temp.next
    }
    println()
}

fun initNode(): NodeList {
    var head = NodeList(-1)
    var temp = head
    for (i in 1..10) {
        temp.next = NodeList(i)
        temp = temp.next as NodeList
    }
    return head
}