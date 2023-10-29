package edu.hw3.Task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task4 {
    private Task4() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        ConverterToRoman.initRomanNumbers();
        int number = 1999;
        String str = ConverterToRoman.convertToRoman(number);
        LOGGER.info(number + " = " + str);
    }
}
