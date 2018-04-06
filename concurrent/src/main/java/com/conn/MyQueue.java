package com.conn;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Yuanp on 2018/4/6.
 */
public class MyQueue {
    //1 需要一个装元素的集合
    private LinkedList<Object> list = new LinkedList<>();

    //2 需要一个计数器
    private AtomicInteger count = new AtomicInteger(0);

    //需要制定上限和下限
    private final int minSize = 0;
    private final int maxSize;

    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    // 初始化一个对象，用于加锁
    private final Object lock = new Object();

    //put(anObject): 把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断，直到BlockingQueue里面有空间再继续.
    public void put(Object obj) {
        synchronized (lock) {
            while (count.get() == maxSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //加入元素
            list.push(obj);
            //计数器累加
            count.incrementAndGet();
            //通知另外一个线程(唤醒)
            lock.notify();
            System.out.println("新加入的元素为：" + obj);
        }
    }

    //take: 取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到BlockingQueue有新的数据被加入.
    public Object take() {
        Object ret = null;
        synchronized (lock) {
            while (count.get() == minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //做移除元素操作
            ret = list.removeFirst();
            //计算器递减
            count.decrementAndGet();
            // 唤醒另外一个线程
            lock.notify();
        }
        return ret;
    }

    public int getSize() {
        return this.count.get();
    }

    public static void main(String[] args) {
        final MyQueue myQueue = new MyQueue(5);
        myQueue.put("a");
        myQueue.put("b");
        myQueue.put("c");
        myQueue.put("d");
        myQueue.put("e");

        System.out.println("当前容器的长度：" + myQueue.getSize());

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myQueue.put("f");
                myQueue.put("g");
            }
        }, "t1");

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("移除的元素为：" + myQueue.take());
                System.out.println("移除的元素为：" + myQueue.take());
            }
        }, "t2");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
    }
}



























