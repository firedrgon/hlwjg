package com.design015;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Yuanp on 2018/4/21.
 */
public class Worker implements Runnable {
    private ConcurrentLinkedQueue<Task> workQueue;
    private ConcurrentHashMap<String,Object> resultMap;

    @Override
    public void run() {
        while (true) {
            Task input = this.workQueue.poll();
            if (input == null) {
                break;
            }
            Object output = this.handle(input);
            this.resultMap.put(Integer.toString(input.getId()), output);
        }
    }

    private Object handle(Task input) {
        Object output = null;
        try {
            //处理耗时操作
            Thread.sleep(500);
            output = input.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }

    public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }
}



















