package com.conn;

import java.util.concurrent.TimeUnit;

/**
 * 双重检查
 * Created by Yuanp on 2018/4/7.
 */
public class DubbleSingleton {
    private static DubbleSingleton ds;

    private DubbleSingleton() {

    }

    public static DubbleSingleton getInstance() {
        if (ds == null) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (DubbleSingleton.class) {
                if (ds == null) {
                    ds = new DubbleSingleton();
                }
            }
        }
        return ds;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DubbleSingleton.getInstance().hashCode());
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DubbleSingleton.getInstance().hashCode());
            }
        }, "t2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DubbleSingleton.getInstance().hashCode());
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}


















