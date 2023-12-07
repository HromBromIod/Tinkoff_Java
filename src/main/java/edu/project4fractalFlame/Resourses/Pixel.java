package edu.project4fractalFlame.Resourses;

public class Pixel {
    private int hitCounter;
    private RGB rgbColour;
    private double normal;

    public Pixel() {
        this.hitCounter = 0;
        this.rgbColour = new RGB(0, 0, 0);
        //this.rgbColour = new RGB();
    }

    public Pixel(int countOfHits, RGB colour, double normal) {
        this.hitCounter = countOfHits;
        this.rgbColour = colour;
        this.normal = normal;
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

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }
}
