package com.design016;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Yuanp on 2018/4/21.
 */
public class Consumer implements Runnable {
    private BlockingQueue<Data> queue;

    private Random random = new Random();

    public Consumer(BlockingQueue<Data> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Data take = this.queue.take();
                Thread.sleep(random.nextInt(1000));
                System.out.println("当前消耗的线程：" + Thread.currentThread().getName() + ",消费成功，消费数据id：" + take.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
























