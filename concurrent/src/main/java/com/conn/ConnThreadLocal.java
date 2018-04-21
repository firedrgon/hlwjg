package com.conn;

/**
 * Created by Yuanp on 2018/4/7.
 */
public class ConnThreadLocal {
    public static ThreadLocal<String> th = new ThreadLocal<>();

    public void setTh(String value) {
        th.set(value);
    }

    public void getTh() {
        System.out.println(Thread.currentThread().getName() + ":" + th.get());
    }

    public static void main(String[] args) {
        final ConnThreadLocal tl = new ConnThreadLocal();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                tl.setTh("张三");
                tl.getTh();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                tl.getTh();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}

































