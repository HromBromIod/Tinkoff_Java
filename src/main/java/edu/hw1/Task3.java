package edu.hw1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task3 {

    private Task3() {
    }

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

    //main
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int lenL = in.nextInt();
        int[] arrL = new int[lenL];
        for (int i = 0; i < lenL; ++i) {
            arrL[i] = in.nextInt();
        }
        int lenR = in.nextInt();
        int[] arrR = new int[lenR];
        for (int i = 0; i < lenR; ++i) {
            arrR[i] = in.nextInt();
        }
        LOGGER.info(isNestable(arrL, arrR));
        in.close();
    }
}
