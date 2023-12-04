package edu.project4fractalFlame.Resourses;

public class Pixel {
    private int hitCounter;
    private RGB rgbColour;

    public Pixel() {
        this.hitCounter = 0;
        this.rgbColour = new RGB(0, 0, 0);
    }

    public Pixel(int countOfHits, RGB colour) {
        this.hitCounter = countOfHits;
        this.rgbColour = colour;
    }

    public void incrementCounter() {
        hitCounter += 1;
    }

    public int getHitCounter() {
        return hitCounter;
    }

    public RGB getColour() {
        return rgbColour;
    }
}
