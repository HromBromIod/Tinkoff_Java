package edu.project4fractalFlame.Functions;

import edu.project4fractalFlame.Resourses.Coefficients;
import edu.project4fractalFlame.Resourses.RGB;
import edu.project4fractalFlame.Resourses.Variation;
import java.util.List;

public record Function(Coefficients coefficients, RGB colour, Coefficients finalTransform, List<Variation> variations) {
}
