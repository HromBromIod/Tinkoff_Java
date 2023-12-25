package edu.hw7.Task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;
import static edu.hw7.Task4.Circle.isPointInCircle;

@SuppressWarnings({"RegexpSinglelineJava", "UncommentedMain", "MagicNumber"})
public class Task4 {
    private Task4() {
    }

    //Среднее время ускорения решения (1_000_000_000): 1.8718092751097823
    //One Thread PI: 3.141212; Погрешность: 3.8065358979322284E-4
    //Multi Thread PI: 3.1416058085664234; Погрешность: 1.3154976630236348E-5

    //Среднее время ускорения решения (100_000_000): 1.547336522434407
    //One Thread PI: 3.14171124; Погрешность: 1.1858641020667804E-4
    //Multi Thread PI: 3.1414632656585306; Погрешность: 1.2938793126249237E-4

    //Среднее время ускорения решения (10_000_000): 1.3151861139680816
    //One Thread PI: 3.141286; Погрешность: 3.066535897930933E-4
    //Multi Thread PI: 3.1419412; Погрешность: 3.4854641020709565E-4

    private static final long TEN_MILLIONS = 10_000_000;

    public static void main(String[] args) throws InterruptedException {
        double expected;
        var start = System.nanoTime();
        expected = oneThreadPI(TEN_MILLIONS * 1);
        var second1 = System.nanoTime() - start;
        System.out.printf("One Thread PI: %s%n", expected);
        System.out.println(Math.abs(expected - Math.PI));
        start = System.nanoTime();
        expected = fourThreadsPI(TEN_MILLIONS * 1);
        var second2 = System.nanoTime() - start;
        System.out.printf("Multi Thread PI: %s%n", expected);
        System.out.println(Math.abs(expected - Math.PI));
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
        ThreadWithCounter[] threads = new ThreadWithCounter[count_of_threads];

        for (int i = 0; i < count_of_threads; ++i) {
            threads[i] = new ThreadWithCounter(countOfIterations / count_of_threads);
            threads[i].start();
            totalCount.add(countOfIterations / count_of_threads);
        }
        for (ThreadWithCounter thread : threads) {
            thread.join();
            pointInCircleCount.add(thread.getCounter());
        }

        return (4.0 * pointInCircleCount.longValue()) / totalCount.longValue();
    }
}
