package edu.hw7.Task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;
import static edu.hw7.Task4.Circle.isPointInCircle;

public class ThreadWithCounter extends Thread {
    private int counter = 0;

    public ThreadWithCounter() {
        super();
    }

    public ThreadWithCounter(Runnable runnable) {
        super(runnable);
    }

    public int getCounter() {
        return counter;
    }

    public Runnable threadPiRunnable(LongAdder pointInCircleCount, long countOfIterationsPerThread) {
        return () -> {
            for (long i = 0; i < countOfIterationsPerThread; ++i) {
                Point p = new Point(ThreadLocalRandom.current().nextDouble(), ThreadLocalRandom.current().nextDouble());
                if (isPointInCircle(p)) {
                    pointInCircleCount.increment();
                }
                counter += 1;
            }
        };
    }
}
