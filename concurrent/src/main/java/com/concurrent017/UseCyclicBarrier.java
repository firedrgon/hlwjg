package com.concurrent017;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Yuanp on 2018/4/22.
 */
public class UseCyclicBarrier {
    static class Runner  implements Runnable{
        private CyclicBarrier barrier;
        private String name;

        public Runner(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000 * (new Random()).nextInt(5));
                System.out.println(name + " 准备OK.");
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name + " GO!!");
        }
    }

    public static void main(String[] args) {
        final CyclicBarrier barrier = new CyclicBarrier(3);
        ExecutorService pool = Executors.newFixedThreadPool(3);

//        pool.submit(new Thread(new Runner(barrier, "zhangsan")));
//        pool.submit(new Thread(new Runner(barrier, "lizi")));
//        pool.submit(new Thread(new Runner(barrier, "wangwu")));

        pool.submit(new Runner(barrier, "zhangsan"));
        pool.submit(new Runner(barrier, "lizi"));
        pool.submit(new Runner(barrier, "wangwu"));

        pool.shutdown();
    }
}

















