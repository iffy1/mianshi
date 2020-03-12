package com.iffy.mianshi.basic.duilie

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingDeque
import java.util.concurrent.BlockingQueue
import java.util.concurrent.PriorityBlockingQueue

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
    val arrayBlockingQueue = ArrayBlockingQueue<Int>(2)

}