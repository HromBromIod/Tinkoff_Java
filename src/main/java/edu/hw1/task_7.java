package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class task_7 {
    public static String intToBinary(int num) {
        String result = null;
        return  result;
    }

    public static int binaryToInt(String bin) {
        int result = -1;
        return result;
    }
    public static int rotateRight(int num, int shift) {
        String bin = intToBinary(num);
        if (bin != null)
        {
            //for ()

                num = binaryToInt(bin);
        }
        return num;
    }
    public static int rotateLeft(int num, int shift) {
        return num;
    }
    private final static Logger LOGGER = LogManager.getLogger();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LOGGER.info("Input number:");
        String number = in.nextLine();
        int num = Integer.parseInt(number);
        LOGGER.info(rotateRight(num, 2));
        LOGGER.info(rotateLeft(num, 2));
        in.close();
    }
}
