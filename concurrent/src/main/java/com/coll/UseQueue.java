package com.coll;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Yuanp on 2018/4/7.
 */
public class UseQueue {
    public static void main(String[] args) throws InterruptedException {
        //高性能无阻塞无界队列
//        ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<>();
//        q.offer("a");
//        q.offer("b");
//        q.offer("c");
//        q.offer("d");
//        q.add("e");
//
//        System.out.println(q.poll());	//a 从头部取出元素，并从队列里删除
//        System.out.println(q.size());	//4
//        System.out.println(q.peek());	//b
//        System.out.println(q.size());	//4
//
//        ArrayBlockingQueue<String> array = new ArrayBlockingQueue<String>(5);
//        array.put("a");
//        array.put("b");
//        array.add("c");
//        array.add("d");
//        array.add("e");
//        array.add("f");
        //System.out.println(array.offer("a", 3, TimeUnit.SECONDS));


//        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
//        queue.offer("a");
//        queue.offer("b");
//        queue.offer("c");
//        queue.offer("d");
//        queue.offer("e");
//        queue.add("f");
        //System.out.println(q.size());

//		for (Iterator iterator = q.iterator(); iterator.hasNext();) {
//			String string = (String) iterator.next();
//			System.out.println(string);
//		}

//        List<String> list = new ArrayList<String>();
//        System.out.println(queue.drainTo(list, 3));
//        System.out.println(list.size());
//        for (String string : list) {
//            System.out.println(string);
//        }

        final SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(synchronousQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronousQueue.add("hello world");
            }
        }, "t2");
        t2.start();
    }
}













































