package com.syn;

/**
 * 出现异常，锁自动释放
 * Created by Yuanp on 2018/4/4.
 */
public class SyncException {
    private int i = 0;

    public synchronized void operation() {
        while (true) {
            try {
                i++;
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " , i = " + i);
                if (i == 20) {
                    Integer.parseInt("a");
                }
            } catch (InterruptedException e) {
                //记录异常日志，可回滚，或重新执行失败的记录两种处理异常方式
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final SyncException se = new SyncException();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                se.operation();
            }
        }, "t1");

        t1.start();
    }
}























