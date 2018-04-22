package com.concurrent016;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yuanp on 2018/4/21.
 */
public class ScheduledJob {
    public static void main(String[] args) {
        Temp temp = new Temp();

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        //运行时，等待3秒执行，然后每隔5秒执行一次
        pool.scheduleWithFixedDelay(temp, 3, 5, TimeUnit.SECONDS);
    }
}

class Temp extends Thread{
    @Override
    public void run() {
        System.out.println("run");
    }
}
