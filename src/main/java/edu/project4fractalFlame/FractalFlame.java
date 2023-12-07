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
import java.util.stream.IntStream;
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
    private final double gamma;
    private final boolean symmetry;

    public FractalFlame(
        int resolutionX,
        int resolutionY,
        int samples,
        int countOfIterations,
        int countOfThreads,
        double gamma,
        boolean symmetry
    ) {
        RESOLUTION_X = resolutionX;
        RESOLUTION_Y = resolutionY;
        this.countOfThreads = countOfThreads;
        this.samplesPerThread = samples / countOfThreads;
        this.iterationsPerSample = countOfIterations;
        this.gamma = gamma;
        this.symmetry = symmetry;
        executorService = Executors.newFixedThreadPool(countOfThreads);
        displayMatrix = new Pixel[RESOLUTION_X][RESOLUTION_Y];
        for (int i = 0; i < RESOLUTION_X; ++i) {
            for (int j = 0; j < RESOLUTION_Y; ++j) {
                displayMatrix[i][j] = new Pixel();
            }
        }
    }

    public Pixel[][] gammaCorrection(Pixel[][] display) {
        var tasks = IntStream.range(0, countOfThreads)
            .mapToObj(numberOfThreads -> CompletableFuture.runAsync(
                    () -> {
                        double max = 0;
                        for (int x = numberOfThreads; x < RESOLUTION_X; x += countOfThreads) {
                            for (int y = 0; y < RESOLUTION_Y; ++y) {
                                if (display[x][y].getHitCounter() != 0) {
                                    display[x][y].setNormal(Math.log10(display[x][y].getHitCounter()));
                                    if (display[x][y].getNormal() > max) {
                                        max = display[x][y].getNormal();
                                    }
                                }
                            }
                        }
                        for (int x = numberOfThreads; x < RESOLUTION_X; x += countOfThreads) {
                            for (int y = 0; y < RESOLUTION_Y; ++y) {
                                display[x][y].setNormal(display[x][y].getNormal() / max);
                                double gammaCoefficient = Math.pow(display[x][y].getNormal(), (1.0 / gamma));
                                display[x][y].getColour().setRed(
                                    (int) (display[x][y].getColour().getRed() * gammaCoefficient)
                                );
                                display[x][y].getColour().setGreen(
                                    (int) (display[x][y].getColour().getGreen() * gammaCoefficient)
                                );
                                display[x][y].getColour().setBlue(
                                    (int) (display[x][y].getColour().getBlue() * gammaCoefficient)
                                );
                            }
                        }
                    },
                    executorService
                )
            )
            .limit(countOfThreads)
            .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(tasks).join();
        return display;
    }

    public void renderFractalFlame() {
        //renderPerThread();
        var tasks = Stream.generate(() -> CompletableFuture.runAsync(
                this::renderPerThread,
                executorService
            ))
            .limit(countOfThreads)
            .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(tasks).join();
    }

    private void renderPerThread() {
        for (int i = 0; i < iterationsPerSample; ++i) {
            final double x_MIN = -((double) RESOLUTION_X / RESOLUTION_Y);
            final double x_MAX = (double) RESOLUTION_X / RESOLUTION_Y;
            final int y_MIN = -1;
            final int y_MAX = 1;
            double newX = ThreadLocalRandom.current().nextDouble(x_MIN, x_MAX);
            double newY = ThreadLocalRandom.current().nextDouble(y_MIN, y_MAX);
            final int MINIMAL_COUNT_OF_ITERATIONS = 20;
            for (int j = -MINIMAL_COUNT_OF_ITERATIONS; j < samplesPerThread; ++j) {
                Function function = Functions.getFunction();
                Point point = tramsformPoint(function, newX, newY, j);
                newX = point.x();
                newY = point.y();
                if (j >= 0 && point.isPointlInRange(x_MIN, x_MAX, y_MIN, y_MAX)) {
                    double x = ((point.x() - x_MIN) / (x_MAX - x_MIN) * RESOLUTION_X);
                    double y = ((point.y() - y_MIN) / (y_MAX - y_MIN) * RESOLUTION_Y);
                    if (isPointOnDisplay(new Point(x, y))) {
                        try {
                            lock.lock();
                            int X = (int) x;
                            int Y = (int) y;
                            displayMatrix[X][Y].incrementCounter();
                            displayMatrix[X][Y].getColour()
                                .setRed((displayMatrix[X][Y].getColour().getRed() + function.colour().getRed()) / 2);
                            displayMatrix[X][Y].getColour()
                                .setGreen(
                                    (displayMatrix[X][Y].getColour().getGreen() + function.colour().getGreen()) / 2);
                            displayMatrix[X][Y].getColour()
                                .setBlue(
                                    (displayMatrix[X][Y].getColour().getBlue() + function.colour().getBlue()) / 2);
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
            function.finalTransform().a() * fixedX + function.finalTransform().b() * fixedY +
                function.finalTransform().c();
        newY =
            function.finalTransform().d() * fixedX + function.finalTransform().e() * fixedY +
                function.finalTransform().f();

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
