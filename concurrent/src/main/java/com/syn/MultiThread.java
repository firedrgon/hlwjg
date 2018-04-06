package com.syn;

/**
 * 关键字synchronized取得的锁都是对象锁，而不是把一段代码(方法)当做锁，
 * 所以代码中哪个线程先执行synchronized关键字的方法，哪个线程就持有该方法所属的锁(Lock)
 *
 * 在静态方法上加synchronized关键字，表示锁定.class类，类一级别的锁(独占.class类)
 * Created by Yuanp on 2018/4/2.
 */
public class MultiThread {
    private int num = 0;

    /** static */
    public synchronized void printNum(String tag) {
        try {
            if ("a".equals(tag)) {
                num = 100;
                System.out.println("tag a, set num over!");
                Thread.sleep(1000);
            } else {
                num = 200;
                System.out.println("tag b, set num over!");
            }
            System.out.println("tag " + tag + ", num = " + num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final MultiThread m1 = new MultiThread();
        final MultiThread m2 = new MultiThread();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                m1.printNum("a");
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                m2.printNum("b");
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}


























