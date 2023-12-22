package edu.project4fractalFlame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Project4Tests {
    private Project4Tests() {
    }

    @Test
    @DisplayName("illegal argument test 1")
    void illegalArgumentTest1() {
        assertThrows(IllegalArgumentException.class, () -> new FractalFlame(-1, 1080, 25_000, 5_000, 6, true));
    }

    @Test
    @DisplayName("illegal argument test 2")
    void illegalArgumentTest2() {
        assertThrows(IllegalArgumentException.class, () -> new FractalFlame(1920, -1, 25_000, 5_000, 6, true));
    }

    @Test
    @DisplayName("illegal argument test 3")
    void illegalArgumentTest3() {
        assertThrows(IllegalArgumentException.class, () -> new FractalFlame(1920, 1080, -1, 5_000, 6, true));
    }

    @Test
    @DisplayName("illegal argument test 4")
    void illegalArgumentTest4() {
        assertThrows(IllegalArgumentException.class, () -> new FractalFlame(1920, 1080, 25_000, -1, 6, true));
    }

    @Test
    @DisplayName("illegal argument test 5")
    void illegalArgumentTest5() {
        assertThrows(IllegalArgumentException.class, () -> new FractalFlame(1920, 1080, 25_000, 5_000, -1, true));
    }

    @Test
    @DisplayName("render picture is not null test")
    void renderPictureIsNotNullTest() {
        assertNotNull(new FractalFlame(1920, 1080, 25_000, 5_000, 5_000, true).getDisplayMatrix());
    }

    @Test
    @DisplayName("render picture after gamma is not null test")
    void renderPictureAfterGammaIsNotNullTest() {
        FractalFlame flame = new FractalFlame(1920, 1080, 25_000, 5_000, 5_000, true);
        assertNotNull(flame.gammaCorrection(flame.getDisplayMatrix(), 0.7));
    }

    @Test
    @DisplayName("illegal gamma coefficient test")
    void illegalGammaCoefficientTest() {
        FractalFlame flame = new FractalFlame(1920, 1080, 25_000, 5_000, 5_000, true);
        assertThrows(IllegalArgumentException.class, () -> flame.gammaCorrection(flame.getDisplayMatrix(), 0));
    }
}
