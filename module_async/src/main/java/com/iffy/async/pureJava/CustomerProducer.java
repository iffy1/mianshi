package com.iffy.async.pureJava;

import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.JobKt;

/**
 * author : iffy
 * time   : 2020/03/20
 */
public class CustomerProducer {
    private static Buffer mBuf = new Buffer();

    private static class Buffer {
        private static final int MAX_CAPACITY = 2;
        private List innerList = new ArrayList<>(MAX_CAPACITY);

        void add() {
            if (isFull()) {
                throw new IndexOutOfBoundsException();
            } else {
                innerList.add(new Object());
            }
            System.out.println(Thread.currentThread().toString() + " add");

        }

        void remove() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException();
            } else {
                innerList.remove(0);
            }
            System.out.println(Thread.currentThread().toString() + " remove");
        }

        boolean isEmpty() {
            return innerList.isEmpty();
        }

        boolean isFull() {
            return innerList.size() == MAX_CAPACITY;
        }
    }

    public static void main(String[] args) {

        Runnable runProduce = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (mBuf) {
                        while (mBuf.isFull()) {
                            try {
                                System.out.println("Produce wait");
                                mBuf.notify();
                                mBuf.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("Produce add");
                        mBuf.add();
                        //mBuf.notify();
                    }
                }
            }
        };

        Runnable runConsume = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (mBuf) {
                        while (mBuf.isEmpty()) {
                            try {
                                System.out.println("Consume wait");
                                mBuf.notify();
                                mBuf.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("Consume remove");
                        mBuf.remove();
                        //mBuf.notify();
                    }
                }
            }
        };

        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(runConsume);
            t.setName("Consumer-" + i);
            t.start();
        }
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(runProduce);
            t.setName("Producer-" + i);
            t.start();
        }
    }

}
