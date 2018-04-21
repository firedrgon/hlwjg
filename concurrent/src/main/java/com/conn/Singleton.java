package com.conn;

/**
 * Created by Yuanp on 2018/4/7.
 */
public class Singleton {

    private Singleton() {

    }
    private static class InnerSingleton{
        private static Singleton singleton = new Singleton();
    }

    public static Singleton getInstance() {
        return InnerSingleton.singleton;
    }
}
