package edu.hw7.Task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;
import static edu.hw7.Task4.Circle.isPointInCircle;

@SuppressWarnings({"RegexpSinglelineJava", "UncommentedMain", "MagicNumber"})
public class Task4 {
    private Task4() {
    }

    //Среднее время ускорения решения (1_000_000_000): 1.8718092751097823
    //One Thread PI: 3.141631592; Погрешность: 3.8938410206856844E-5
    //Multi Thread PI: 3.1416058085664234; Погрешность: 1.3154976630236348E-5

    //Среднее время ускорения решения (100_000_000): 1.547336522434407
    //One Thread PI: 3.14171124; Погрешность: 1.1858641020667804E-4
    //Multi Thread PI: 3.1414632656585306; Погрешность: 1.2938793126249237E-4

    //Среднее время ускорения решения (10_000_000): 0.953596004543705
    //One Thread PI: 3.1416276; Погрешность: 3.4946410206959655E-5
    //Multi Thread PI: 3.1411734564693825; Погрешность: 4.191971204106082E-4

    private static final long TEN_MILLIONS = 10_000_000;

    public static void main(String[] args) throws InterruptedException {
        double expected;
        var start = System.nanoTime();
        expected = oneThreadPI(TEN_MILLIONS);
        System.out.printf("One Thread PI: %s%n", expected);
        System.out.println(Math.abs(expected - Math.PI));
        var second1 = System.nanoTime() - start;
        start = System.nanoTime();
        expected = fourThreadsPI(TEN_MILLIONS);
        System.out.printf("Multi Thread PI: %s%n", expected);
        System.out.println(Math.abs(expected - Math.PI));
        var second2 = System.nanoTime() - start;
        System.out.println((double) second1 / second2);
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
        final int count_of_threads = 4;
        Runnable oneThreadPILambda = (() -> {
            for (long i = 0; i < countOfIterations / count_of_threads; ++i) {
                Point p = new Point(ThreadLocalRandom.current().nextDouble(), ThreadLocalRandom.current().nextDouble());
                if (isPointInCircle(p)) {
                    pointInCircleCount.increment();
                }
            }
        });
        ThreadWithCounter thread1 = new ThreadWithCounter(oneThreadPILambda);
        ThreadWithCounter thread2 = new ThreadWithCounter(oneThreadPILambda);
        ThreadWithCounter thread3 = new ThreadWithCounter(oneThreadPILambda);
        ThreadWithCounter thread4 = new ThreadWithCounter(oneThreadPILambda);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        totalCount.add(thread1.getCounter());
        totalCount.add(thread2.getCounter());
        totalCount.add(thread3.getCounter());
        totalCount.add(thread4.getCounter());
        return (4.0 * pointInCircleCount.longValue()) / totalCount.longValue();
    }
}
