package com.iffy.async.pureJava;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueTest {

    private static BlockingQueue<Food> queue = new ArrayBlockingQueue<Food>(10);

    class Producer implements Runnable {
        int i;

        Producer(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            while (true) {
                Food food = new Food("banana");
                try {
                    food.setName("banana");
                    queue.put(food);
                    System.out.println(Thread.currentThread().getName() + "provider " + i + "正在生产 ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable {
        int i = 0;

        Consumer(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Food food = queue.take();
                    System.out.println(Thread.currentThread().getName() + "consumer " + i + "正在消费");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(new ArrayBlockingQueueTest().new Producer(i)).start();
        }

        new Thread(new ArrayBlockingQueueTest().new Consumer(0)).start();
        new Thread(new ArrayBlockingQueueTest().new Consumer(1)).start();
    }
}