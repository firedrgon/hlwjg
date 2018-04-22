package com.concurrent017;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Yuanp on 2018/4/22.
 */
public class UseSemaphore {
    public static void main(String[] args) {
        //线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        //只能5个线程同时访问
        final Semaphore semp = new Semaphore(5);

        for (int index = 0; index < 20; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        //获取许可
                        semp.acquire();
                        System.out.println("Accessing:" + NO);
                        Thread.sleep((long) (Math.random() * 10000));
                        //访问完后，释放
                        semp.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            pool.execute(run);
        }

        pool.shutdown();
    }
}
