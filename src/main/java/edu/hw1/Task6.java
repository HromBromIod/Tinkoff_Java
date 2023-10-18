package edu.hw1;

import java.util.Arrays;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task6 {
    private static final int KAPREKAR = 6174;
    private static final int THOUSAND = 1000;
    private static final int HUNDRED = 100;
    private static final int TEN = 10;
    private static final int THREE = 3;

    private Task6() {
    }

    public static int countK(int num) {
        Integer result = 0;
        if (num != KAPREKAR) {
            int[] newNum = new int[] {num % TEN, num % HUNDRED / TEN, num % THOUSAND / HUNDRED, num / THOUSAND};
            int count = 1;
            Arrays.sort(newNum);
            int numR = newNum[0] + newNum[1] * TEN + newNum[2] * HUNDRED + newNum[THREE] * THOUSAND;
            int numL = newNum[THREE] + newNum[2] * TEN + newNum[1] * HUNDRED + newNum[0] * THOUSAND;
            result = 1 + countK(numR - numL);
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
        LOGGER.info(countK(num));
        in.close();
    }
}
