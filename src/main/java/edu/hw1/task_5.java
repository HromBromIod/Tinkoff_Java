package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class task_5 {
    public static int getDescendant(int num) {
        int result = 0, count = 1;
        while (num > 99) {
            result = result + ((num % 10) + (num % 100 / 10)) * count;
            count *= 10;
            num /= 100;
        }
        if (num > 9) {
            result = result + ((num / 10) + (num % 10)) * count;
        } else {
            result = result + num * count;
        }
        return result;
    }

    public static boolean isPalindromeDescendant(int num) {
        boolean result = false;
        while (num > 9 && !result) {
            boolean checkPalindrome = true;
            int numCheck = num;
            while (numCheck > 0 && checkPalindrome) {
                int f = numCheck, count = 1;
                while (f > 9) {
                    f /= 10;
                    count *= 10;
                }
                if (f != numCheck % 10) {
                    checkPalindrome = false;
                } else {
                    numCheck = numCheck - f * count;
                    numCheck /= 10;
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

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LOGGER.info("Input number:");
        String number = in.nextLine();
        int num = Integer.parseInt(number);
        LOGGER.info(isPalindromeDescendant(num));
        in.close();
    }
}
