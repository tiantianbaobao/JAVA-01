package com.tiantianbaobao.baby.simple;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/***
 * @description simple http server, use while to handle each request
 * @author <h>cuitao@aixuexi.com</h>
 * @date 2021-01-17 21:36
 * @since V1.0.0
 */
public class HttpServer1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8081);
        while(true){
            Socket accept = serverSocket.accept();
            serverHandle(accept);
        }
    }

    private static void serverHandle(Socket accept) throws IOException, InterruptedException {
        TimeUnit.MILLISECONDS.sleep(20);
        PrintWriter printWriter = new PrintWriter(accept.getOutputStream(), false);
        printWriter.write("Http/1.1 200 OK");
        printWriter.write("Content-type:text/html;charset=utf-8");
        printWriter.println();
        printWriter.write("hello~~");
        printWriter.close();
        accept.close();
    }
}
