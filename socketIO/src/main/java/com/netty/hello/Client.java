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

        ChannelFuture channelFuture1 = bootstrap.connect("127.0.0.1", 8765).sync();
        ChannelFuture channelFuture2 = bootstrap.connect("127.0.0.1", 8764).sync();
        //发送消息
        Thread.sleep(1000);
        //发送消息
        channelFuture1.channel().write(Unpooled.copiedBuffer("hello netty".getBytes()));
        channelFuture2.channel().write(Unpooled.copiedBuffer("8888".getBytes()));
        //flush的时候才发送信息到服务端
        channelFuture2.channel().flush();
        channelFuture1.channel().flush();

        channelFuture2.channel().closeFuture().sync();
        channelFuture1.channel().closeFuture().sync();

        loopGroup.shutdownGracefully();
    }
}




















