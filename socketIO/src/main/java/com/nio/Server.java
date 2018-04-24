package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Yuanp on 2018/4/22.
 */
public class Server implements Runnable {
    //多路复用器(管理所有的通道)
    private Selector selector;
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);

    public Server(int port) {
        try {
            //打开路复用器
            this.selector = Selector.open();
            //打开服务器通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
            //设置服务器通道为非阻塞模式
            ssc.configureBlocking(false);
            //绑定地址
            ssc.bind(new InetSocketAddress(port));
            ssc.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 必须要让多路复用器开始监听
                this.selector.select();
                //返回多路复用器已经选择的结果集
                Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    //获取一个选择的元素
                    SelectionKey key = keys.next();
                    //直接从容器中移除就可以了
                    keys.remove();
                    //如果是有效的
                    if (key.isValid()) {
                        //如果为阻塞状态
                        if (key.isAcceptable()) {
                            this.accept(key);
                        }
                        //如果为可读状态
                        if (key.isReadable()) {
                            this.read(key);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey key) {
        try {
            //清空缓冲区旧的数据
            this.readBuffer.clear();
            //获取之前注册的socket通道对象
            SocketChannel sc = (SocketChannel) key.channel();
            //读取数据
            int count = sc.read(this.readBuffer);
            //如果没有数据
            if (count == -1) {
                key.channel().close();
                key.cancel();
                return;
            }

            //有数据则进行读取，读取之前需要进行复位方法
            this.readBuffer.flip();
            //根据缓冲区的数据长度创建相应大小的byte数组，接收缓冲区的数据
            byte[] bytes = new byte[this.readBuffer.remaining()];
            //接收缓冲区的数据
            this.readBuffer.get(bytes);
            //打印结果
            String body = new String(bytes).trim();
            System.out.println("server:" + body);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void accept(SelectionKey key) {
        try {
            //获取服务通道
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            //执行阻塞方法
            SocketChannel sc = ssc.accept();
            //设置阻塞模式
            sc.configureBlocking(false);
            //注册到多路复用器上，并设置读取标识
            sc.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new Server(8765)).start();
    }
}

























