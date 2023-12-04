package edu.project4fractalFlame.Resourses;

import java.util.concurrent.ThreadLocalRandom;

public class RGB {
    private int red;
    private int green;
    private int blue;

    public RGB() {
        red = ThreadLocalRandom.current().nextInt(256);
        green = ThreadLocalRandom.current().nextInt(256);
        blue = ThreadLocalRandom.current().nextInt(256);
    }

    public RGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRGB() {
        return (red << 16 | green << 8 | blue);
    }
}
