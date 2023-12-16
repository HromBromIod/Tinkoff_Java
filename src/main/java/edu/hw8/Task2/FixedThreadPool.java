package edu.hw8.Task2;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Stream;

public class FixedThreadPool implements ThreadPool {
    private BlockingQueue<Runnable> blockingQueue;
    private Thread[] threads;

    public FixedThreadPool(int numberOfThreads) {
        create(numberOfThreads);
    }

    private void create(int numberOfThreads) {
        blockingQueue = new LinkedBlockingDeque<>(numberOfThreads);
        threads = Stream.generate(() -> new Thread(() -> {
                while (true) {
                    try {
                        Runnable task = blockingQueue.take();
                        task.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            })).limit(numberOfThreads)
            .toArray(Thread[]::new);
    }

    @Override
    public void start() {
        Arrays.stream(threads).forEach(Thread::start);
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            blockingQueue.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        Arrays.stream(threads).forEach(Thread::interrupt);
    }
}
