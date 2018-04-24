package com.lock02;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Yuanp on 2018/4/22.
 */
public class UseReentrantReadWriteLock {
    //读写锁
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    //读锁
    private ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    //写锁
    private ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

    public void read() {
        try {
            readLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }
    }

    public void write() {
        try {
            writeLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        final UseReentrantReadWriteLock readWriteLock = new UseReentrantReadWriteLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.read();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.read();
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.write();
            }
        }, "t3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.write();
            }
        }, "t4");

//        t1.start();
//        t2.start();

//        t1.start();
//        t3.start();

        t3.start();
        t4.start();
    }
}

























