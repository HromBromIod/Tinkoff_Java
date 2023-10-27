package edu.hw3.Task8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Iterator;
import java.util.List;
import edu.hw3.Task8.BackwardIterator.*;

public class Task8 {
    private Task8() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        BackwardIterator<Integer> it = new BackwardIterator<>(List.of(1, 2, 3));



        LOGGER.info("");
    }
}
