package com.iffy.mianshi.thread

//如果线程内使用sleep的函数使线程挂起，这时又调用了Interrupt()函数 就会导致异常抛出（java.lang.InterruptedException: sleep interrupted）
//
//方法1 Boolean值 中断
//方法2 isInterrupted() 中断

fun main() {

    var runanle = Runnable {
        run {
            while (true) {
                println("我去")
                //Thread.sleep(2000)
                if(Thread.interrupted()){
                    return@run
                }
            }
        }
    }
    var sThread = Thread(runanle)
    sThread.start()
    Thread.sleep(2000)
    sThread.interrupt()


}

