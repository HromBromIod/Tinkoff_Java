package edu.hw3.Task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task4 {
    private static final int NUMBER = 1999;

    private Task4() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        String str = ConverterToRoman.convertToRoman(NUMBER);
        LOGGER.info(NUMBER + " = " + str);
    }
}
