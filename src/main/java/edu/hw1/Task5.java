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

    public static int getDescendant(int num) {
        int result = 0, count = 1;
        while (num > NINETYNINE) {
            result = result + ((num % TEN) + (num % HUNDRED / TEN)) * count;
            count *= TEN;
            num /= HUNDRED;
        }
        if (num > NINE) {
            result = result + ((num / TEN) + (num % TEN)) * count;
        } else {
            result = result + num * count;
        }
        return result;
    }

    public static boolean isPalindromeDescendant(int num) {
        boolean result = false;
        while (num > NINE && !result) {
            boolean checkPalindrome = true;
            int numCheck = num;
            while (numCheck > 0 && checkPalindrome) {
                int f = numCheck, count = 1;
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
