package com.iffy.async.pureJava;

/**
 * author : iffy
 * time   : 2020/03/18
 */
public class MultiThreadLocalTest {
    public static void main(String[] args) {
        final ThreadLocal tl1= new ThreadLocal();
        final ThreadLocal tl2 = new ThreadLocal();

        new Thread(new Runnable(){
            @Override
            public void run() {
                tl1.set(123);
                tl2.set("aaa");

                System.out.println(tl1.get());
                System.out.println(tl2.get());
            }
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                tl1.set(234);
                tl2.set("vvv");

                System.out.println(tl1.get());
                System.out.println(tl2.get());
            }
        }).start();
    }

}
