package edu.hw1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task5 {
    private static final int HUNDRED = 100;
    private static final int NINETYNINE = 99;
    private static final int TEN = 10;
    private static final int NINE = 9;

    private Task5() {
    }

    public static int getDescendant(int value) {
        int result = 0;
        int count = 1;
        int num = value;
        while (num > NINE) {
            if ((num % TEN) + (num % HUNDRED / TEN) > 9 && count != 1) {
                count *= 10;
            }
            result = result + ((num % TEN) + (num % HUNDRED / TEN)) * count;
            count *= TEN;
            num /= HUNDRED;
        }
        if (num > 0) {
            result = result + num * count;
        }
        return result;
    }

    public static boolean isPalindromeDescendant(int value) {
        boolean result = false;
        int num = value;
        while (num > NINE && !result) {
            boolean checkPalindrome = true;
            int numCheck = num;
            while (numCheck > 0 && checkPalindrome) {
                int f = numCheck;
                int count = 1;
                while (f > NINE) {
                    f /= TEN;
                    count *= TEN;
                }
                if (f != numCheck % TEN) {
                    checkPalindrome = false;
                } else {
                    numCheck = numCheck - f * count;
                    numCheck /= TEN;
                }
            }
            if (!checkPalindrome) {
                num = getDescendant(num);
            } else {
                result = true;
            }
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
        LOGGER.info(isPalindromeDescendant(num));
        in.close();
    }
}
