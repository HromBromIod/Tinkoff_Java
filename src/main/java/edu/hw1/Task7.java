package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

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

    public static int rotateRight(int num, int shift) {
        if (num >= 0) {
            if (shift < 0) {
                num = rotateLeft(num, Math.abs(shift));
            } else {

                //for ()
            }
        }
        return num;
    }

    public static int rotateLeft(int num, int shift) {

        return num;
    }

    private final static Logger LOGGER = LogManager.getLogger();

    //main
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LOGGER.info("Input number:");
        String number = in.nextLine();
        int num = Integer.parseInt(number);
        LOGGER.info(rotateRight(num, 2));
        //LOGGER.info(rotateLeft(num, 2));
        in.close();
    }
}
