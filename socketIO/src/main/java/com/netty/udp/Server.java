package com.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * Created by Yuanp on 2018/4/30.
 */
public class Server {
    public void run(int port) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new ServerHandler());
        bootstrap.bind(port).sync().channel().closeFuture().await();
    }
    public static void main(String[] args) throws InterruptedException {
        new Server().run(8765);
    }
}
