package com.iffy.mianshi.多线程

var lockA = Object()
var lockB = Object()

//死锁指的是多个进程/线程循环等待其它地方占有的资源而无限地僵持下去的局面，其根本原因是对有限资源的操作不当。

//产生死锁有四个 必要条件，只要有一个条件不满足，死锁就可以排除：
//
//互斥条件：某个资源在一段时间内只能由一个进程/线程占有，不能被两个或以上的进程/线程占有。
//不可抢占条件：进程/线程所获得的资源在未使用完毕之前，资源申请者不能强行从资源占有者手中抢夺资源，而只能由资源占有者自行释放。
//占有且申请条件：进程/线程至少已经占有一个条件，但又申请新的资源；由于该资源已经被其它占有，此时该进程/线程阻塞；但是它在等待新的资源时，仍然占有已用的资源。
//环路等待：存在一个等待序列{P1, P2, ..Pn}，其中P1等待P2所占有的资源，P2等待P3占有的资源，...，而Pn等待P1所占有的资源，形成一个循环等待环。



//死锁案例
fun main() {
        Thread(workerA()).start()
        Thread(workerB()).start()
}

class workerA : Runnable {
    override fun run() {
        while (true) {
            println("workerA 等待A锁")
            synchronized(lockA) {
                println("workerA 持有A锁 等待B锁")
                synchronized(lockB) {
                }
            }
        }
    }
}

class workerB : Runnable {
    override fun run() {
        while (true) {
            println("workerB 等待B锁")
            synchronized(lockB) {
                println("workerB 持有B锁 等待A锁")
                synchronized(lockA) {
                }
            }
        }
    }
}