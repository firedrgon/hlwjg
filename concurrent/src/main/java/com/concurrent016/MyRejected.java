package com.concurrent016;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Yuanp on 2018/4/21.
 */
public class MyRejected implements RejectedExecutionHandler {
    public MyRejected() {
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("自定义拒绝策略");
        System.out.println("当前拒绝的任务是：" + r.toString());
    }
}
