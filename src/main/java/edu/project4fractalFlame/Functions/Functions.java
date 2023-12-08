package edu.project4fractalFlame.Functions;

import edu.project4fractalFlame.Fractal.Fractal;
import edu.project4fractalFlame.Resourses.Coefficients;
import edu.project4fractalFlame.Resourses.RGB;
import edu.project4fractalFlame.Resourses.Variation;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Functions {
    private Functions() {
    }

    private static final Coefficients defaultCoefficients = new Coefficients(1, 0, 0, 0, 1, 0);
    private static final Coefficients defaulttFinalTransformation =
        new Coefficients(1, 0, 0, 0, 1, 0);

    private static final List<Function> FUNCTIONS = List.of(
        new Function(
            Coefficients.randomCoefficients(),
            new RGB(),
            Coefficients.randomCoefficients(),
            List.of(
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.HEART),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.EXPONENTIAL),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.POPCORN),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.SWIRL),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.DISC)
            )
        ),
        new Function(
            Coefficients.randomCoefficients(),
            new RGB(),
            Coefficients.randomCoefficients(),
            List.of(
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.FISHEYE),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.SPHERICAL),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.TANGENT)
            )
        ),
        new Function(
            Coefficients.randomCoefficients(),
            new RGB(),
            Coefficients.randomCoefficients(),
            List.of(
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.EYEFISH),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.BUBBLE),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.TANGENT)
            )
        ),
        new Function(
            Coefficients.randomCoefficients(),
            new RGB(),
            Coefficients.randomCoefficients(),
            List.of(
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.HEART),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.EXPONENTIAL),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.POPCORN),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.DISC)
            )
        ),
        new Function(
            Coefficients.randomCoefficients(),
            new RGB(),
            Coefficients.randomCoefficients(),
            List.of(
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.HEART),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.DIAMOND)
            )
        ),
        new Function(
            Coefficients.randomCoefficients(),
            new RGB(),
            Coefficients.randomCoefficients(),
            List.of(
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.HEART),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.DIAMOND),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.WAVES)
            )
        ),
        new Function(
            Coefficients.randomCoefficients(),
            new RGB(),
            Coefficients.randomCoefficients(),
            List.of(
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.HEART),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.SINUSOIDAL),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.DISC)
            )
        ),
        new Function(
            Coefficients.randomCoefficients(),
            new RGB(),
            Coefficients.randomCoefficients(),
            List.of(
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.HORSESHO),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.HANDKERCHIEF)
            )
        ),
        new Function(
            Coefficients.randomCoefficients(),
            new RGB(),
            Coefficients.randomCoefficients(),
            List.of(
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.HEART),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.SPIRAL)
            )
        ),
        new Function(
            Coefficients.randomCoefficients(),
            new RGB(),
            Coefficients.randomCoefficients(),
            List.of(
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.HEART),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.EXPONENTIAL)
            )
        ),
        new Function(
            Coefficients.randomCoefficients(),
            new RGB(),
            Coefficients.randomCoefficients(),
            List.of(
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.SPIRAL),
                new Variation(ThreadLocalRandom.current().nextInt(1, 3), Fractal.HYPERBOLIC)
            )
        )
    );

    public static Function getFunction() {
        return FUNCTIONS.get(ThreadLocalRandom.current().nextInt(FUNCTIONS.size()));
    }
}
