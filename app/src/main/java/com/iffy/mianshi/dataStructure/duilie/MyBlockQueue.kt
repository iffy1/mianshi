package com.iffy.mianshi.dataStructure.list.duilie

import java.util.concurrent.*

/**
 *     author : iffy
 *     time   : 2020/03/09
 */
class MyBlockQueue {

}
fun main(){
    //BlockingQueue即阻塞队列，它是基于ReentrantLock，依据它的基本原理，
    // 我们可以实现Web中的长连接聊天功能，
    // 当然其最常用的还是用于实现生产者与消费者模式，大致如下图所示：

    val priorityBlockingQueue = PriorityBlockingQueue<Int>(2)
    val arrayBlockingQueue = ArrayBlockingQueue<Int>(5)//超出容量会报异常
    arrayBlockingQueue.add(1)
    arrayBlockingQueue.add(2)
    arrayBlockingQueue.add(3)//区别于栈 删除时候从队首 添加从队尾

    arrayBlockingQueue.forEach{
        println(it)
    }
    arrayBlockingQueue.remove()//区别于栈 删除时候从队首 添加从队尾
    arrayBlockingQueue.forEach{
        println(it)
    }

    val linkedBlockingDeque = LinkedBlockingDeque<String>()//没有容量限制
    linkedBlockingDeque.add("一")
    linkedBlockingDeque.add("二")
    linkedBlockingDeque.add("三")//区别于栈 删除时候从队首 添加从队尾

    linkedBlockingDeque.forEach{
        println(it)
    }
    linkedBlockingDeque.remove()//区别于栈 删除时候从队首 添加从队尾
    linkedBlockingDeque.forEach{
        println(it)
    }


}