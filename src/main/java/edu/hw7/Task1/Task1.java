package edu.hw7.Task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    private Task1() {
    }

    public static int parallelIncrement(int countOfThreads) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        for (int i = 0; i < countOfThreads; ++i) {
            Thread thread = new Thread(counter::incrementAndGet);
            thread.start();
            thread.join();
        }
        return counter.get();
    }
}
