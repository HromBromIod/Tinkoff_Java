package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class task_3 {
    public static boolean isNestable(int[] arrL, int[] arrR) {
        boolean result = false;
        int maxL = 1, maxR = 1, minL = -1, minR = -1;
        for (int i = 0; i < arrL.length; ++i) {
            if (i == 0) {
                maxR = arrR[i];
                minR = arrR[i + 1];
                maxL = arrL[i];
                minL = arrL[i];
            } else {
                if (maxL < arrL[i]) {
                    maxL = arrL[i];
                }
                if (minL > arrL[i]) {
                    maxL = arrL[i];
                }
            }
        }
        if (maxR < minR) {
            int x = minR;
            minR = maxR;
            maxR = x;
        }
        if (maxR > maxL && minR < minL) {
            result = true;
        }
        return result;
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] arrL = new int[] {0, 6};
        int[] arrR = new int[] {-1, 7};
        LOGGER.info(isNestable(arrL, arrR));
        in.close();
    }
}
