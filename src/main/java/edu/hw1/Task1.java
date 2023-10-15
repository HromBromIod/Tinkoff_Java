package edu.hw1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain") public class Task1 {
    private static final int SIXTY = 60;

    private Task1() {
    }

    public static boolean isTime(String time) {
        boolean result = true;
        for (int i = 0; i < time.indexOf(":") && result; ++i) {
            if (time.charAt(i) > '9' || time.charAt(i) < '0') {
                result = false;
            }
        }
        int countSeconds = 0;
        for (int i = time.indexOf(":") + 1; i < time.length() && result; ++i) {
            if (time.charAt(i) > '9' || time.charAt(i) < '0') {
                result = false;
            }
            countSeconds += 1;
        }
        if (countSeconds != 2) {
            result = false;
        }
        if (result) {
            int sec = Integer.parseInt(time.substring(time.indexOf(":") + 1, time.length()));
            if (sec >= SIXTY || (time.length() - time.indexOf(":") - 1 != 2)) {
                result = false;
            }
        }
        return result;
    }

    public static int timeToSecond(String time) {
        int seconds = 0;
        if (isTime(time)) {
            int min = Integer.parseInt(time.substring(0, time.indexOf(":")));
            int sec = Integer.parseInt(time.substring(time.indexOf(":") + 1, time.length()));
            seconds = min * SIXTY + sec;
        } else {
            seconds = -1;
        }
        return seconds;
    }

    private final static Logger LOGGER = LogManager.getLogger();

    //main
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LOGGER.info("Input time:");
        String time = in.nextLine();
        LOGGER.info(timeToSecond(time));
        in.close();
    }
}
