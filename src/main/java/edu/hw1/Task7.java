package edu.hw1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task7 {
    private Task7() {
    }

    public static String intToBinary(int num) {
        String result = "1";
        return result;
    }

    public static int binaryToInt(String bin) {
        int result = -1;
        return result;
    }

    public static int rotateRight(int value, int shift) {
        int num = value;
        if (num > 0) {
            if (shift < 0) {
                num = rotateLeft(num, Math.abs(shift));
            } else {
                String binary = Integer.toBinaryString(num);
                int length = binary.length();
                for (int i = 0; i < shift; ++i) {
                    binary = binary.charAt(length - 1) + binary.substring(0, length - 1);
                }
                num = Integer.parseInt(binary, 2);
            }
        }
        return num;
    }

    public static int rotateLeft(int value, int shift) {
        int num = value;
        if (num > 0) {
            if (shift < 0) {
                num = rotateRight(num, Math.abs(shift));
            } else {
                String binary = Integer.toBinaryString(num);
                int length = binary.length();
                for (int i = 0; i < shift; ++i) {
                    binary = binary.substring(1, length) + binary.charAt(0);
                }
                num = Integer.parseInt(binary, 2);
            }
        }
        return num;
    }

    private final static Logger LOGGER = LogManager.getLogger();

    //main
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LOGGER.info("Input number:");
        String number = in.nextLine();
        int num = Integer.parseInt(number);
        //LOGGER.info(rotateRight(num, 2));
        LOGGER.info(rotateLeft(num, 2));
        in.close();
    }
}
