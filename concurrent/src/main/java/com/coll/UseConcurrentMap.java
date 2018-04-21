package com.coll;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Yuanp on 2018/4/7.
 */
public class UseConcurrentMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<>();

        chm.put("k1", "v1");
        chm.put("k2", "v2");
        chm.put("k3", "v3");
        chm.putIfAbsent("k3", "vvvv");

        for (Map.Entry<String, String> entry : chm.entrySet()) {
            System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
        }

    }
}





















