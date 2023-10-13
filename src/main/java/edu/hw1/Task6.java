package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class Task6 {
    public static int countK(int num) {
        int result = 0;
        if (num != 6174) {
            int numR = 0, numL = 0;
            int[] newNum = new int[] {num % 10, num % 100 / 10, num % 1000 / 100, num / 1000};
            int count = 1;
            for (int i = 3; i >= 1; --i) {
                for (int j = i - 1; j >= 0; --j) {
                    if (newNum[i] < newNum[j]) {
                        int x = newNum[i];
                        newNum[i] = newNum[j];
                        newNum[j] = x;
                    }
                }
                numR += newNum[i];
                numR *= 10;
                numL += (newNum[i] * count);
                count *= 10;
            }
            numR += newNum[0];
            numL += (newNum[0] * count);
            num = numR - numL;
            result = 1 + countK(num);
        }
        return result;
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LOGGER.info("Input number:");
        String number = in.nextLine();
        int num = Integer.parseInt(number);
        LOGGER.info(countK(num));
        in.close();
    }
}
