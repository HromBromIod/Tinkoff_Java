package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class task_1 {

    public static boolean is_time(String time) {
        boolean result = true;
        for (int i = 0; i < time.indexOf(":") && result; ++i)
            if (time.charAt(i) > '9' || time.charAt(i) < '0')
                result = false;
        for (int i = time.indexOf(":") + 1; i < time.length() && result; ++i)
            if (time.charAt(i) > '9' || time.charAt(i) < '0')
                result = false;
        if (result)
        {
            int sec = Integer.parseInt(time.substring(time.indexOf(":") + 1, time.length()));
            if (sec >= 60 || (time.length() - time.indexOf(":") - 1 != 2))
                result = false;
        }
        return result;
    }
    public static int timeToSecond(String time) {
        int seconds = 0;
        if (is_time(time))
        {
            int min = Integer.parseInt(time.substring(0, time.indexOf(":")));
            int sec = Integer.parseInt(time.substring(time.indexOf(":") + 1, time.length()));
            seconds = min * 60 + sec;
        }
        else
            seconds = -1;
        return seconds;
    }
    private final static Logger LOGGER = LogManager.getLogger();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LOGGER.info("Input time:");
        String time = in.nextLine();
        LOGGER.info(timeToSecond(time));
        in.close();
    }
}
