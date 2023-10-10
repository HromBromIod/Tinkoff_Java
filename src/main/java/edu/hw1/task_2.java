package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class task_2 {
    public static int countDigits(int num) {
        int result = 1;
        while (Math.abs(num) > 9)
        {
            num /= 10;
            result += 1;
        }
        return result;
    }
    private final static Logger LOGGER = LogManager.getLogger();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LOGGER.info("Input number:");
        String number = in.nextLine();
        int num = Integer.parseInt(number);
        LOGGER.info(countDigits(num));
        in.close();
    }
}
