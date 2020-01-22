package com.iffy.async.threadPool


import java.util.concurrent.Executors.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

//Android中的线程池概念来源于Java中的Executor，Executor是一个接口，真正的线程的实现为ThreadPoolExecutor
//AsyncTask的底层实现ThreadPoolExecutor
//从线程池的功能特性上来说，Android中线程池主要分为4类：
//    FixedThreadPool(线程数量固定)、
//    CachedThreadPool(线程数量不定)、
//    ScheduledThreadPool(核心线程数量固定，非核心线程数量无限制)、
//    SingleThreadExecutor(只有一个核心线程，所有任务在里面按顺序执行)

class ThreadPoolExecutor {

//        corePoolSize: 线程池的核心线程数，默认情况下， 核心线程会在线程池中一直存活， 即使处于闲置状态. 但如果将allowCoreThreadTimeOut设置为true的话, 那么核心线程也会有超时机制， 在keepAliveTime设置的时间过后， 核心线程也会被终止.
//        maximumPoolSize: 最大的线程数， 包括核心线程， 也包括非核心线程， 在线程数达到这个值后，新来的任务将会被阻塞.
//        keepAliveTime: 超时的时间， 闲置的非核心线程超过这个时长，讲会被销毁回收， 当allowCoreThreadTimeOut为true时，这个值也作用于核心线程.
//        unit：超时时间的时间单位.
//        workQueue：线程池的任务队列， 通过execute方法提交的runnable对象会存储在这个队列中.
//        threadFactory: 线程工厂, 为线程池提供创建新线程的功能.
//        handler: 任务无法执行时，回调handler的rejectedExecution方法来通知调用者.

    fun main() {
        var a = ThreadPoolExecutor(
            1, 1,
            0L, TimeUnit.MILLISECONDS, LinkedBlockingQueue<Runnable>()
        )
        a.execute {
            while (true) {
                println("working")
            }
        }

        //特点：只有核心线程数，并且没有超时机制，因此核心线程即使闲置时，也不会被回收，因此能更快的响应外界的请求.
        newFixedThreadPool(1)

        //特点：没有核心线程，非核心线程数量没有限制， 超时为60秒.
        //适用于执行大量耗时较少的任务，当线程闲置超过60秒时就会被系统回收掉，当所有线程都被系统回收后，它几乎不占用任何系统资源.
        newCachedThreadPool()

        //特点：核心线程数是固定的，非核心线程数量没有限制， 没有超时机制. 主要用于执行定时任务和具有固定周期的重复任务.
        newScheduledThreadPool(1)

        //特点：只有一个核心线程，并没有超时机制. 意义在于统一所有的外界任务到一个线程中， 这使得在这些任务之间不需要处理线程同步的问题.
        newSingleThreadExecutor()
    }


}








