package com.syn;

/**
 * synchronized的重入
 * Created by Yuanp on 2018/4/4.
 */
public class SyncDubbo1 {
    public synchronized void method1() {
        System.out.println("method1..");
        method2();
    }

    public synchronized void method2() {
        System.out.println("method2..");
        method3();
    }

    public synchronized void method3() {
        System.out.println("method3..");
    }

    public static void main(String[] args) {
        final SyncDubbo1 dubbo1 = new SyncDubbo1();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dubbo1.method1();
            }
        }, "t1");

        t1.start();
    }
}
































