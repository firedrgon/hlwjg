package com.syn;

/**
 * 业务整体需要使用完整的synchronized，保持业务的原子性
 * Created by Yuanp on 2018/4/3.
 */
public class DirtyRead {
    private String username = "bjsxt";
    private String password = "123";

    public void setValue(String username, String password) {
        this.username = username;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.password = password;
        System.out.println("setValue最终结果是:username = " + username + " , password = " + password);
    }

    public void getValue() {
        System.out.println("getValue方法得到: username = " + this.username + " , password = " + this.password);
    }

    public static void main(String[] args) throws InterruptedException {
        final DirtyRead dr = new DirtyRead();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dr.setValue("zhansan", "666");
            }
        }, "t1");
        t1.start();

        Thread.sleep(1000);

        dr.getValue();
    }
}






















