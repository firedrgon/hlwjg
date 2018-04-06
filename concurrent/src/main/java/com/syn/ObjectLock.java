package com.syn;

/**
 * 使用synchronized代码块加锁灵活
 * Created by Yuanp on 2018/4/5.
 */
public class ObjectLock {
    public void method1() {
        synchronized (this) {  //对象锁
            try {
                System.out.println("method1..");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2() {
        synchronized (ObjectLock.this) {  //类锁
            try {
                System.out.println("method2..");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Object lock = new Object();

    public void method3() {
        synchronized (lock) {  //任何对象的锁
            try {
                System.out.println("do method3..");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final ObjectLock objLock = new ObjectLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method1();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method2();
            }
        }, "t2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method3();
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }

}












