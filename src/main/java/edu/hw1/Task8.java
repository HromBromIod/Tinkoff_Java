package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task8 {
    private static final int EIGHT = 8;

    private Task8() {
    }

    public static boolean checkLeft(int[][] desk, int i, int j) {
        boolean result = false;
        if (i - 1 >= 0 && j - 2 >= 0 && desk[i - 1][j - 2] == 1
            || i + 1 < EIGHT && j - 2 >= 0 && desk[i + 1][j - 2] == 1) {
            result = true;
        }
        return result;
    }

    public static boolean checkRight(int[][] desk, int i, int j) {
        boolean result = false;
        if (i - 1 >= 0 && j + 2 < EIGHT && desk[i - 1][j + 2] == 1
            || i + 1 < EIGHT && j + 2 < EIGHT && desk[i + 1][j + 2] == 1) {
            result = true;
        }
        return result;
    }

    public static boolean checkAbove(int[][] desk, int i, int j) {
        boolean result = false;
        if (i - 2 >= 0 && j - 1 >= 0 && desk[i - 2][j - 1] == 1
            || i - 2 >= 0 && j + 1 < EIGHT && desk[i - 2][j + 1] == 1) {
            result = true;
        }
        return result;
    }

    public static boolean checkUnder(int[][] desk, int i, int j) {
        boolean result = false;
        if (i + 2 < EIGHT && j - 1 >= 0 && desk[i + 2][j - 1] == 1
            || i + 2 < EIGHT && j + 1 < EIGHT && desk[i + 2][j + 1] == 1) {
            result = true;
        }
        return result;
    }

    public static boolean knightBoardCapture(int[][] desk) {
        boolean result = true;
        for (int i = 0; i < EIGHT && result; ++i) {
            for (int j = 0; j < EIGHT && result; ++j) {
                if (desk[i][j] == 1) {
                    if (checkLeft(desk, i, j) || checkRight(desk, i, j) || checkAbove(desk, i, j)
                        || checkUnder(desk, i, j)) {
                        result = false;
                    }
                }
            }
        }
        return result;
    }

    private final static Logger LOGGER = LogManager.getLogger();

    //main
    public static void main(String[] args) {
        int[][] desk = {{0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}};
        LOGGER.info(knightBoardCapture(desk));
    }
}
