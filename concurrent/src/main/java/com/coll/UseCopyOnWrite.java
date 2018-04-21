package com.coll;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Yuanp on 2018/4/7.
 */
public class UseCopyOnWrite {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> cwal = new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet<String> cwas = new CopyOnWriteArraySet<>();
    }
}
