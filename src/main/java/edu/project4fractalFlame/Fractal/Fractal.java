package edu.project4fractalFlame.Fractal;

import edu.project4fractalFlame.Resourses.Point;

@SuppressWarnings("MagicNumber")
public class Fractal {
    private Fractal() {
    }

    public static final Pattern LINEAR =
        (coefficients, point) -> new Point(point.x(), point.y());

    public static final Pattern SINUSOIDAL =
        (coefficients, point) -> new Point(Math.sin(point.x()), Math.sin(point.y()));

    public static final Pattern CYLINDER =
        (coefficients, point) -> new Point(Math.sin(point.x()), point.y());

    public static final Pattern WAVES =
        (coefficients, point) -> new Point(
            point.x() + coefficients.b() * Math.sin(point.y() / coefficients.c() * coefficients.c()),
            point.y() + coefficients.e() * Math.sin(point.x() / coefficients.f() * coefficients.f())
        );

    public static final Pattern SPHERICAL =
        (coefficients, point) -> {
            double rSquared = point.x() * point.x() + point.y() * point.y();
            return new Point(point.x() / rSquared, point.y() / rSquared);
        };

    public static final Pattern SWIRL =
        (coefficients, point) -> {
            double rSquared = point.x() * point.x() + point.y() * point.y();
            double sinRSquared = Math.sin(rSquared);
            double cosRSquared = Math.cos(rSquared);
            return new Point(
                point.x() * sinRSquared - point.y() * cosRSquared,
                point.x() * cosRSquared + point.y() * sinRSquared
            );
        };

    public static final Pattern HORSESHO =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            return new Point(
                (point.x() * point.x() - point.y() * point.y()) / r,
                2 * point.x() * point.y() / r
            );
        };

    public static final Pattern POPCORN =
        (coefficients, point) -> new Point(
            point.x() + coefficients.c() * Math.sin(Math.tan(3 * point.y())),
            point.y() + coefficients.f() * Math.sin(Math.tan(3 * point.x()))
        );

    public static final Pattern BUBBLE =
        (coefficients, point) -> {
            double rSquared = point.x() * point.x() + point.y() * point.y();
            return new Point(4 * point.x() / (rSquared + 4), 4 * point.y() / (rSquared + 4));
        };

    public static final Pattern FISHEYE =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            return new Point(2 * point.y() / (r + 1), 2 * point.x() / (r + 1));
        };

    public static final Pattern EYEFISH =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            return new Point(2 * point.x() / (r + 1), 2 * point.y() / (r + 1));
        };

    public static final Pattern TANGENT =
        (coefficients, point) -> new Point(
            Math.sin(point.x()) / Math.cos(point.y()),
            Math.tan(point.y())
        );

    public static final Pattern EXPONENTIAL =
        (coefficients, point) -> new Point(
            Math.exp(point.x() - 1) * Math.cos(Math.PI * point.y()),
            Math.exp(point.x() - 1) * Math.sin(Math.PI * point.y())
        );

    public static final Pattern HANDKERCHIEF =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            double theta = Math.atan(point.x() / point.y());
            return new Point(r * Math.sin(theta + r), r * Math.cos(theta - r));
        };

    public static final Pattern HEART =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            double theta = Math.atan(point.x() / point.y());
            return new Point(r * Math.sin(theta * r), r * (-1) * Math.cos(theta * r));
        };

    public static final Pattern DISC =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            double theta = Math.atan(point.x() / point.y());
            return new Point(theta / Math.PI * Math.sin(Math.PI * r), theta / Math.PI * Math.cos(Math.PI * r));
        };

    public static final Pattern HYPERBOLIC =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            double theta = Math.atan(point.x() / point.y());
            return new Point(Math.sin(theta) / r, r * Math.cos(theta));
        };

    public static final Pattern DIAMOND =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            double theta = Math.atan(point.x() / point.y());
            return new Point(Math.sin(theta) * Math.cos(r), Math.cos(theta) * Math.sin(r));
        };

    public static final Pattern SPIRAL =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            double theta = Math.atan(point.x() / point.y());
            return new Point((Math.cos(theta) + Math.sin(r)) / r, (Math.sin(theta) - Math.cos(r)) / r);
        };
}
