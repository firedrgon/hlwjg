package com.design015;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by Yuanp on 2018/4/21.
 */
public class Main {
    public static void main(String[] args) {
        Master master = new Master(new Worker(), Runtime.getRuntime().availableProcessors());

        Random random = new Random();
        for (int i = 0; i <= 100; i++) {
            Task task = new Task();
            task.setId(i);
            task.setPrice(BigDecimal.valueOf(random.nextDouble()));
            master.submit(task);
        }

        long start = System.currentTimeMillis();
        //执行
        master.execute();
        while (true) {
            if (master.isComplete()) {
                long end = System.currentTimeMillis();
                BigDecimal result = master.getResult();
                System.out.println("最终结果：" + result + ",执行时间：" + (end - start));
                break;
            }
        }
    }
}
