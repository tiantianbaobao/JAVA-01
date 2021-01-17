package com.tiantianbaobao.baby.simple;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***
 * @description simple http server, use while to handle each request
 * @author <h>cuitao@aixuexi.com</h>
 * // sb -u http://localhost:8083 -c 30 -N 40 -n 100    ->
 * RPS: 1181.4 (requests/second)
 * Max: 367ms
 * Min: 19ms
 * Avg: 21.9ms
 *
 *   50%   below 21ms
 *   60%   below 21ms
 *   70%   below 22ms
 *   80%   below 22ms
 *   90%   below 23ms
 *   95%   below 25ms
 *   98%   below 31ms
 *   99%   below 35ms
 * 99.9%   below 66ms
 * @date 2021-01-17 21:36
 * @since V1.0.0
 */
public class HttpServer3 {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        ServerSocket serverSocket = new ServerSocket(8083);
        while(true){
            Socket accept = serverSocket.accept();
            executorService.execute(() -> {
                try {
                    serverHandle(accept);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
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
