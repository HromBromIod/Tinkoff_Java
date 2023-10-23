package edu.hw3.Task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task4 {
    private Task4() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        ConverterToRoman converter = new ConverterToRoman();
        Integer number = 1999;
        String str = converter.convertToRoman(number);
        LOGGER.info(number + " = " + str);
    }
}
