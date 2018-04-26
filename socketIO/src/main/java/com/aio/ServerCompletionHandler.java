package com.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * Created by Yuanp on 2018/4/25.
 */
public class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,Server>{
    @Override
    public void completed(AsynchronousSocketChannel result, Server attachment) {
        //当有下一个客户端接入的时候 直接调用Server的accept方法
        attachment.assc.accept(attachment, this);

        this.read(result);
    }

    private void read(final AsynchronousSocketChannel asc) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        asc.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                //进行读取之后，重置标识为
                attachment.flip();
                System.out.println("Server - >" + "收到客户端的数据长度为：" + result);
                //获取读取的数据
                String resultData = new String(attachment.array()).trim();
                System.out.println("Server -> " + "收到客户端的数据信息为:" + resultData);

                String response = "服务器响应, 收到了客户端发来的数据: " + resultData;

                //响应信息到客户端
                write(asc, response);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }

    private void write(final AsynchronousSocketChannel asc, String response) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(response.getBytes());
            buffer.flip();
            asc.write(buffer).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, Server attachment) {
        exc.printStackTrace();
    }
}
