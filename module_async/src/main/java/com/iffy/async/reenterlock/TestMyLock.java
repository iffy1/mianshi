package com.iffy.async.reenterlock;

/**
 * author : iffy
 * time   : 2020/03/22
 */
public class TestMyLock {
    static MyLock myLock = new MyLock();
    public static void main(String[] args) {

        try {
            call();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    call();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    call();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public static void call() throws InterruptedException {
        myLock.lock();
        System.out.println("make a call");
        sms();
        myLock.unlock();
    }

    public static void sms() throws InterruptedException {
        myLock.lock();
        System.out.println("send sms");
        myLock.unlock();

    }

}
