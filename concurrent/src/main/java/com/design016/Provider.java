package com.design016;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Yuanp on 2018/4/21.
 */
public class Provider implements Runnable {
    //共享缓冲区
    private BlockingQueue<Data> queue;
    //多线程间是否启动变量，有强制从主内存中刷新的功能，即时返回线程的状态
    private volatile boolean isRunning = true;
    private static AtomicInteger count = new AtomicInteger(0);
    private static Random random = new Random();

    public Provider(BlockingQueue<Data> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                //随机休眠
                Thread.sleep(random.nextInt(1000));
                //获取的数据进行累加
                int id = count.incrementAndGet();
                Data data = new Data(Integer.toString(id),"数据" + id);
                System.out.println("当前线程：" + Thread.currentThread().getName() + ", 获取了数据，id为:" + id + ", 进行装载到公共缓冲区中...");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("提交缓冲区数据失败");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.isRunning = false;
    }
}
























