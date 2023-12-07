package edu.project4fractalFlame.Fractal;

import edu.project4fractalFlame.Resourses.Coefficients;
import edu.project4fractalFlame.Resourses.Point;

public interface Pattern {
    Point apply(Coefficients coefficients, Point point);
}
