package edu.project4fractalFlame;

import edu.project4fractalFlame.Drawer.FractalFlameDrawer;
import edu.project4fractalFlame.Resourses.Pixel;

@SuppressWarnings("MagicNumber")
public class Main {
    private Main() {
    }

    public static void main(String[] args) {
        int resolutionX = 1920;
        int resolutionY = 1080;
        FractalFlame flame = new FractalFlame(resolutionX, resolutionY, 25_000, 5_000, 6, true);
        flame.renderFractalFlame();
        Pixel[][] image = flame.gammaCorrection(flame.getDisplayMatrix(), 0.7);
        FractalFlameDrawer drawerFlame = new FractalFlameDrawer(resolutionX, resolutionY);
        drawerFlame.drawFlame(image);
        drawerFlame.saveToFile();
    }
}
