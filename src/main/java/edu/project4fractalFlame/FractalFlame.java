package edu.project4fractalFlame;

import edu.project4fractalFlame.Functions.Function;
import edu.project4fractalFlame.Functions.Functions;
import edu.project4fractalFlame.Resourses.Pixel;
import edu.project4fractalFlame.Resourses.Point;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public class FractalFlame {
    private final int RESOLUTION_X;
    private final int RESOLUTION_Y;
    private final int countOfThreads;
    private final int samplesPerThread;
    private final int iterationsPerSample;
    private final ReentrantLock lock = new ReentrantLock();
    private final ExecutorService executorService;
    private final Pixel[][] displayMatrix;
    private final boolean symmetry;

    public FractalFlame(
        int resolutionX,
        int resolutionY,
        int samples,
        int countOfIterations,
        int countOfThreads,
        boolean symmetry
    ) {
        RESOLUTION_X = resolutionX;
        RESOLUTION_Y = resolutionY;
        this.countOfThreads = countOfThreads;
        //this.samplesPerThread = samples / countOfThreads;
        this.samplesPerThread = samples;
        this.iterationsPerSample = countOfIterations;
        this.symmetry = symmetry;
        executorService = Executors.newFixedThreadPool(countOfThreads);
        displayMatrix = new Pixel[RESOLUTION_X][RESOLUTION_Y];
        for (int i = 0; i < RESOLUTION_X; ++i) {
            for (int j = 0; j < RESOLUTION_Y; ++j) {
                displayMatrix[i][j] = new Pixel();
            }
        }
    }

    public void renderFractalFlame() {
        renderPerThread();
        /*var tasks = Stream.generate(() -> CompletableFuture.runAsync(
                this::renderPerThread,
                executorService
            ))
            .limit(countOfThreads)
            .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(tasks).join();*/
    }

    private void renderPerThread() {
        for (int i = 0; i < iterationsPerSample; ++i) {
            final double x_MIN = -1.777;
            final double x_MAX = 1.777;
            final int y_MIN = -1;
            final int y_MAX = 1;
            double newX = ThreadLocalRandom.current().nextDouble(x_MIN, x_MAX);
            double newY = ThreadLocalRandom.current().nextDouble(y_MIN, y_MAX);
            final int MINIMAL_COUNT_OF_ITERATIONS = 20;
            for (int j = -MINIMAL_COUNT_OF_ITERATIONS; j < samplesPerThread; ++j) {
                Point point = tramsformPoint(Functions.getFunction(), newX, newY, j);
                newX = point.x();
                newY = point.y();
                if (j >= 0 && point.isPointlInRange(x_MIN, x_MAX, y_MIN, y_MAX)) {
                    int x = (int) ((RESOLUTION_X - newX) / (x_MAX - x_MIN) * RESOLUTION_X);
                    int y = (int) ((RESOLUTION_Y - newY) / (y_MAX - y_MIN) * RESOLUTION_Y);
                    if (isPointOnDisplay(new Point(x, y))) {
                        try {
                            lock.lock();
                            displayMatrix[x][y].incrementCounter();
                            displayMatrix[x][y].getColour()
                                .setRed((displayMatrix[x][y].getColour().getRed() /*+ function.rgb().getRed()*/) / 2);
                            displayMatrix[x][y].getColour()
                                .setGreen(
                                    (displayMatrix[x][y].getColour().getGreen() /*+ function.rgb().getGreen()*/) / 2);
                            displayMatrix[x][y].getColour()
                                .setBlue(
                                    (displayMatrix[x][y].getColour().getBlue() /*+ function.rgb().getBlue()*/) / 2);
                        } finally {
                            lock.unlock();
                        }
                    }
                }
            }
        }
    }

    private Point tramsformPoint(Function function, double x, double y, int iterationNumber) {
        double fixedX = 0;
        double fixedY = 0;
        double newX = function.coefficients().a() * x + function.coefficients().b() * y + function.coefficients().c();
        double newY = function.coefficients().d() * x + function.coefficients().e() * y + function.coefficients().f();

        for (var variation : function.variations()) {
            Point point = variation.pattern().apply(
                function.coefficients(),
                new Point(newX, newY)
            );
            fixedX = variation.weight() * point.x();
            fixedY = variation.weight() * point.y();
        }

        newX =
            function.coefficients().a() * fixedX + function.coefficients().b() * fixedY + function.coefficients().c();
        newY =
            function.coefficients().d() * fixedX + function.coefficients().e() * fixedY + function.coefficients().f();

        if (symmetry) {
            if (iterationNumber % 4 == 0) {
                newX *= -1;
            } else if (iterationNumber % 3 == 0) {
                newX *= -1;
                newY *= -1;
            } else if (iterationNumber % 2 == 0) {
                newY *= -1;
            }
        }
        return new Point(newX, newY);
    }

    public Pixel[][] getDisplayMatrix() {
        return displayMatrix;
    }

    public boolean isPointOnDisplay(Point point) {
        return 0 <= point.x() && point.x() < RESOLUTION_X && 0 <= point.y() && point.y() < RESOLUTION_Y;
    }
}
