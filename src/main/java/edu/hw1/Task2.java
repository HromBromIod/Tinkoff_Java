package edu.hw1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task2 {
    private static final int TEN = 10;
    private static final int NINE = 9;

    private Task2() {
    }

    public static int countDigits(int value) {
        int result = 1;
        int num = value;
        while (Math.abs(num) > NINE) {
            num /= TEN;
            result += 1;
        }
        return result;
    }

    private final static Logger LOGGER = LogManager.getLogger();

    //main
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LOGGER.info("Input number:");
        String number = in.nextLine();
        int num = Integer.parseInt(number);
        LOGGER.info(countDigits(num));
        in.close();
    }
}
