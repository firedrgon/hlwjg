package com.netty.hello;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Yuanp on 2018/4/27.
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {
        //创建两个线程组
        //一个是用于处理服务器端接收客户端连接的
        //一个是进行网络通信的(网络的读写)
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //创建辅助工具类，用于服务器通道的一系列配置
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup) //绑定两个线程组
                .channel(NioServerSocketChannel.class)  //指定NIO的模式
                .option(ChannelOption.SO_BACKLOG,1024) //设置tcp缓冲区
                .option(ChannelOption.SO_KEEPALIVE,true) //保持连接
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //在这里配置具体数据接收方法的处理
                        socketChannel.pipeline().addLast(new ServerHandler());
                    }
                });

        //端口绑定
        ChannelFuture channelFuture = bootstrap.bind(8765).sync();

        //等待关闭
        channelFuture.channel().closeFuture().sync();

        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}



























