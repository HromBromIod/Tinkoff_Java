package edu.hw7.Task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;
import static edu.hw7.Task4.Circle.isPointInCircle;

public class Task4 {
    private Task4() {
    }

    public static double oneThreadPI(long countOfIterations) {
        long pointInCircleCount = 0;
        for (long i = 0; i < countOfIterations; ++i) {
            Point p = new Point(ThreadLocalRandom.current().nextDouble(), ThreadLocalRandom.current().nextDouble());
            if (isPointInCircle(p)) {
                pointInCircleCount += 1;
            }
        }
        return (4.0 * pointInCircleCount) / countOfIterations;
    }

    public static double fourThreadsPI(long countOfIterations) throws InterruptedException {
        LongAdder totalCount = new LongAdder();
        LongAdder pointInCircleCount = new LongAdder();
        final int count_of_threads = 6;
        Runnable oneThreadPILambda = (() -> {
            for (long i = 0; i < countOfIterations / count_of_threads; ++i) {
                Point p = new Point(ThreadLocalRandom.current().nextDouble(), ThreadLocalRandom.current().nextDouble());
                if (isPointInCircle(p)) {
                    pointInCircleCount.increment();
                }
                totalCount.increment();
            }
        });
        Thread thread1 = new Thread(oneThreadPILambda);
        Thread thread2 = new Thread(oneThreadPILambda);
        Thread thread3 = new Thread(oneThreadPILambda);
        Thread thread4 = new Thread(oneThreadPILambda);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        return (4.0 * pointInCircleCount.longValue()) / totalCount.longValue();
    }
}
