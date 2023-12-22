package edu.project4fractalFlame.Resourses;

public record Point(double x, double y) {
    public boolean isPointlInRange(double minX, double maxX, double minY, double maxY) {
        return minX <= x && x < maxX && minY <= y && y < maxY;
    }
}
