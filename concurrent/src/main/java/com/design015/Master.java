package com.design015;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Yuanp on 2018/4/21.
 */
public class Master {
    //有一个盛放任务的容器
    private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<>();
    //需要有一个盛放worker的集合
    private HashMap<String, Thread> workers = new HashMap<>();

    //需要有一个盛放每一个worker执行任务的结果集合
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

    public Master(Worker worker, int workerCount) {
        worker.setWorkQueue(workQueue);
        worker.setResultMap(resultMap);

        for (int i = 0; i < workerCount; i++) {
            workers.put(Integer.toString(i), new Thread(worker));
        }
    }

    //需要一个提交任务的方法
    public void submit(Task task) {
        workQueue.add(task);
    }

    //需要有一个执行的方法，启动所有的worker方法去执行任务
    public void execute() {
        for (Map.Entry<String, Thread> me : workers.entrySet()) {
            me.getValue().start();
        }
    }

    //判断是否允许结束的方法
    public boolean isComplete() {
        for (Map.Entry<String, Thread> me : workers.entrySet()) {
            if (me.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    //获得结果
    public BigDecimal getResult() {
        BigDecimal priceResult = BigDecimal.ZERO;
        for (Map.Entry<String, Object> me : resultMap.entrySet()) {
            priceResult = priceResult.add(new BigDecimal(me.getValue().toString()));
        }
        return priceResult;
    }
}



