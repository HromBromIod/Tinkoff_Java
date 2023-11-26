package edu.hw7.Task4;

public class Circle {
    private Circle() {
    }

    private static final Point CENTER = new Point(0.5, 0.5);
    private static final double RADIUS = 0.5;

    public static boolean isPointInCircle(Point p) {
        return (p.x - CENTER.x) * (p.x - CENTER.x) + (p.y - CENTER.y) * (p.y - CENTER.y) <= RADIUS * RADIUS;
    }
}
