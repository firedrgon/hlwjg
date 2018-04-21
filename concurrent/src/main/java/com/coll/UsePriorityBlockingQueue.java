package com.coll;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Yuanp on 2018/4/7.
 */
public class UsePriorityBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Task> blockingQueue = new PriorityBlockingQueue<>();

        Task t1 = new Task();
        t1.setId(3);
        t1.setName("id为3");
        Task t2 = new Task();
        t2.setId(4);
        t2.setName("id为4");
        Task t3 = new Task();
        t3.setId(1);
        t3.setName("id为1");

        //return this.id > task.id ? 1 : 0;
        blockingQueue.add(t1);	//3
        blockingQueue.add(t2);	//4
        blockingQueue.add(t3);  //1

        System.out.println("容器：" + blockingQueue);
        System.out.println(blockingQueue.take().getId());
        System.out.println("容器：" + blockingQueue);
    }
}



































