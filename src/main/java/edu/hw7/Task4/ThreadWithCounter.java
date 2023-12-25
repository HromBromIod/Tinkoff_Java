package edu.hw7.Task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;
import static edu.hw7.Task4.Circle.isPointInCircle;

public class ThreadWithCounter extends Thread {
    private long counter;
    private final long countOfIterations;

    public ThreadWithCounter(long countOfIterationsPerThread) {
        counter = 0;
        countOfIterations = countOfIterationsPerThread;
    }

    @Override
    public void run() {
        for (long i = 0; i < countOfIterations; ++i) {
            Point p = new Point(ThreadLocalRandom.current().nextDouble(), ThreadLocalRandom.current().nextDouble());
            if (isPointInCircle(p)) {
                counter += 1;
            }
        }
    }

    public long getCounter() {
        return counter;
    }
}
