package edu.hw3.Task8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

@SuppressWarnings("uncommentedmain")
public class Task8 {
    private final static int THREE = 3;

    private Task8() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        BackwardIterator<Integer> it = new BackwardIterator<>(List.of(1, 2, THREE));

        it.next();

        LOGGER.info("");
    }
}
