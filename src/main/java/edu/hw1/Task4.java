package edu.hw1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task4 {

    private Task4() {
    }

    public static String fixString(String line) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < line.length(); i += 2) {
            if (i + 1 >= line.length()) {
                result.append(line.charAt(i));
            } else {
                result.append(line.charAt(i + 1));
                result.append(line.charAt(i));
            }
        }
        return result.toString();
    }

    private final static Logger LOGGER = LogManager.getLogger();

    //main
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LOGGER.info("Input line:");
        String line = in.nextLine();
        LOGGER.info("Input line:\n" + fixString(line));
        in.close();
    }
}
