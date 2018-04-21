package com.design014;

/**
 * Created by Yuanp on 2018/4/19.
 */
public class FutureClient {
    public Data request(final String queryStr) {
        //我想要一个代理对象(Data接口的实现类)，先返回给发送请求的客户端，告诉她请求已经接收到
        final FutureData futureData = new FutureData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //这个新的线程 可以慢慢的加载真实对象，然后传递给代理对象
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }).start();
        return futureData;
    }
}
