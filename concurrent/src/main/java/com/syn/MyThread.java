package com.syn;

/**
 * 线程安全概念：当多个线程访问某一个类(对象或方法)时，这个对象始终都能表现出正确的行为，那么这个类(对象或方法)就是线程安全的
 * synchronized：可以在任意对象及方法上加锁，而加锁的这段代码称为"互斥区"或"临界区"
 * Created by Yuanp on 2018/3/31.
 */
public class MyThread extends Thread{
    private int count = 0;
    @Override
    public synchronized void run() {
        count++;
        System.out.println(this.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        /**
         * 分析：当多个线程访问thread的run方法时，以排队的方式进行处理(这里排队是按照CPU分配的先后顺序而定的)，
         *      一个线程想要执行synchronized修饰的方法里的代码：
         *      1.尝试获得锁
         *      2.如果拿到锁，执行synchronized代码体内容；拿不到锁，这个线程就会不断的尝试获得这把锁，直到拿到为止，
         *      而且是多个线程同时去竞争这把锁。（也就是会又锁竞争的问题）
         */
        MyThread thread = new MyThread();
        Thread thread1 = new Thread(thread, "t1");
        Thread thread2 = new Thread(thread, "t2");
        Thread thread3 = new Thread(thread, "t3");
        Thread thread4 = new Thread(thread, "t4");
        Thread thread5 = new Thread(thread, "t5");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}



















