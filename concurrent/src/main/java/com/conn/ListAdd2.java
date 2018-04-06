package com.conn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * wait notify 方法，wait释放锁，notify不释放锁
 * Created by Yuanp on 2018/4/6.
 */
public class ListAdd2 {
    private volatile static List list = new ArrayList<>();

    public void add() {
        list.add("bjsxt");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final ListAdd2 listAdd1 = new ListAdd2();

        final Object lock = new Object();

        CountDownLatch latch = new CountDownLatch(1);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
//                synchronized (lock) {
                    try {
                        for (int i = 0; i< 10; i++) {
                            listAdd1.add();
                            System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
                            Thread.sleep(500);
                            if (list.size() == 5) {
                                System.out.println("已经发出通知");
//                                lock.notify();
                                latch.countDown();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
//                synchronized (lock) {
                    if (list.size() != 5) {
                        try {
//                            lock.wait();
                            latch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list size = 5 线程停止..");
                        throw new RuntimeException();
                    }
//                }
            }
        }, "t2");

        t2.start();
        t1.start();
    }
}






















