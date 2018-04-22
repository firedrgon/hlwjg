package com.concurrent016;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Yuanp on 2018/4/21.
 */
public class UserThreadPoolExecutor2 extends Thread {
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            int temp = count.incrementAndGet();
            System.out.println("任务" + temp);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> queue =
//                new ArrayBlockingQueue<Runnable>(10);  //有界任务队列
                new LinkedBlockingDeque<>();  //无界任务队列

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                5,
                10,
                120L,
                TimeUnit.SECONDS,
                queue
        );

        //提交20个任务
        for (int i = 0; i < 20; i++) {
            pool.execute(new UserThreadPoolExecutor2());
        }

        Thread.sleep(1000);
        System.out.println("queue size:" + queue.size());
        Thread.sleep(2000);

    }
}













