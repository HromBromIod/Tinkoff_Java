package edu.hw8.Task1;

import java.nio.channels.SocketChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class Server {
    private ExecutorService executorService;
    private BlockingQueue<SocketChannel> blockingQueue;
    public Server(int countOfThreads) {
        executorService = Executors.newFixedThreadPool(countOfThreads);
        blockingQueue = new LinkedBlockingDeque<>(countOfThreads);
    }


}
