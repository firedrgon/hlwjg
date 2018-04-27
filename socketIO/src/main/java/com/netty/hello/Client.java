package com.netty.hello;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Yuanp on 2018/4/27.
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup loopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(loopGroup)
            .channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ClientHandler());
                }
        });

        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8765).sync();
        //发送消息
        Thread.sleep(1000);
        //发送消息
        channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer("8888".getBytes()));

        channelFuture.channel().closeFuture().sync();

        loopGroup.shutdownGracefully();
    }
}




















