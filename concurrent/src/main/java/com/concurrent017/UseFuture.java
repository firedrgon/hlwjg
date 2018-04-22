package com.concurrent017;

import java.util.concurrent.*;

/**
 * Created by Yuanp on 2018/4/22.
 */
public class UseFuture implements Callable<String> {
    private String para;

    public UseFuture(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        String result = this.para + "处理完毕";
        return result;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //构造FutureTask，并且传入需要真正进行业务逻辑处理的类，该类一定是实现了Callable接口
        FutureTask<String> futureTask = new FutureTask<String>(new UseFuture("hello"));

        ExecutorService pool = Executors.newFixedThreadPool(1);
        //submit和execute的区别：submit可以传入实现Callable接口的实例对象，submit有返回值
        Future<?> future = pool.submit(futureTask);

        //o==null,表示执行完毕
        Object o = future.get();
        System.out.println(o);
        System.out.println("请求完毕");

        //这里可以做额外的数据操作，也就是主程序执行其他逻辑
        Thread.sleep(1000);

        System.out.println("异步请求返回的数据:" + futureTask.get());
        pool.shutdown();
    }
}




































