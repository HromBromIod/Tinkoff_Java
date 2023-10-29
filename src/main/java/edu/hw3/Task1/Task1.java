package edu.hw3.Task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task1 {
    private Task1() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        Encoder encoder = new Encoder();
        String str = "Hello world!";
        LOGGER.info(str);
        str = Encoder.atbash(str);
        LOGGER.info(str);
    }
}
