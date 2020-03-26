package com.iffy.async.pureJava;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * author : iffy
 * time   : 2020/03/24
 */
public class ThreadTest {
    public static void main(String[] args) {
        System.out.println("主线程"+Thread.currentThread().getName());
        MyCallable myCallable = new MyCallable();
        new Thread(new FutureTask(myCallable)).start();
    }

    public static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("工作开始"+Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println("工作完成");
            return "";
        }
    }
}
