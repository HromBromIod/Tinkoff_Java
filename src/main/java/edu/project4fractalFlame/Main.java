package edu.project4fractalFlame;

import edu.project4fractalFlame.Drawer.FractalFlameDrawer;
import edu.project4fractalFlame.Resourses.Pixel;

public class Main {
    private Main() {
    }

    public static void main(String[] args) {
        //var start = System.nanoTime();
        FractalFlame flame = new FractalFlame(100, 100, 5_000, 1_500, 4, false);
        flame.renderFractalFlame();
        Pixel[][] image = flame.getDisplayMatrix();
        //var end = System.nanoTime() - start;
        //System.out.println(end);
        FractalFlameDrawer drawerFlame = new FractalFlameDrawer(2560, 1440);
        drawerFlame.drawFlame(image);
        drawerFlame.saveToFile();
    }
}
