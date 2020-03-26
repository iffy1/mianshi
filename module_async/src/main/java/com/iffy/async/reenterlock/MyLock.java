package com.iffy.async.reenterlock;

/**
 * author : iffy
 * time   : 2020/03/22
 * 自定义可重入锁
 */

//可重入锁的实现原理？
//
//看到这里，你终于明白了 synchronized 是一个可重入锁。但是面试官要再问你，可重入锁的原理是什么？
//对不起，你又卡壳了。
//那么我现在先给你说一下，可重入锁的原理。具体我们后面再写 ReentrantLock 的时候来验证或看它源码。
//重入锁实现可重入性原理或机制是：每一个锁关联一个线程持有者和计数器，
// 当计数器为 0 时表示该锁没有被任何线程持有，那么任何线程都可能获得该锁而调用相应的方法；
// 当某一线程请求成功后，JVM会记下锁的持有线程，并且将计数器置为 1；
// 此时其它线程请求该锁，则必须等待；而该持有锁的线程如果再次请求这个锁，就可以再次拿到这个锁，
// 同时计数器会递增；当线程退出同步代码块时，计数器会递减，如果计数器为 0，则释放该锁。

public class MyLock {
    private Thread lockThread;
    private Boolean isLocked = false;
    private int lockCount = 0;

    public synchronized void lock() throws InterruptedException {
        if (isLocked && Thread.currentThread() != lockThread) {
            wait();
        }

        if(lockThread == null || Thread.currentThread() == lockThread){
            lockThread = Thread.currentThread();
            //当前线程再次调用Lock的时候锁计数器加1
            lockCount++;
            isLocked = true;
        }

    }

    public synchronized void unlock() throws InterruptedException {
        if (Thread.currentThread() == lockThread) {
            //锁计数器减1
            lockCount--;
            //如果锁计数器为0时释放锁
            if (lockCount == 0) {
                isLocked = false;
                lockThread = null;
                notify();
            }
        }
    }
}
