package com.bio2;

import com.bio.*;
import com.bio.ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Yuanp on 2018/4/22.
 */
public class Server {
    final static int PORT = 8765;
    public static void main(String[] args) {
        ServerSocket server = null;

        try {
            server = new ServerSocket(PORT);
            System.out.println("Server start ..");
            HandleExecutorPool executorPool = new HandleExecutorPool(50, 100);
            while (true) {
                //进行阻塞，等待客户端的连接
                Socket socket = server.accept();
                executorPool.execute(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(server != null){
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            server = null;
        }
    }
}
