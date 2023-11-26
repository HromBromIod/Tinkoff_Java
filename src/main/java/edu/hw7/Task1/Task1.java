package edu.hw7.Task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    private Task1() {
    }

    public static int parallelIncrement() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        Thread thread1 = new Thread(counter::incrementAndGet);
        Thread thread2 = new Thread(counter::incrementAndGet);
        Thread thread3 = new Thread(counter::incrementAndGet);
        Thread thread4 = new Thread(counter::incrementAndGet);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        return counter.get();
    }
}
