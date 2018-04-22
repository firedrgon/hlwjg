package com.concurrent016;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Yuanp on 2018/4/21.
 */
public class UserExecutors {
    public static void main(String[] args) {
        ExecutorService pool1 = Executors.newCachedThreadPool();
        ExecutorService pool2 = Executors.newFixedThreadPool(2);
    }
}
