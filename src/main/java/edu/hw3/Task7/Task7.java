package edu.hw3.Task7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.TreeMap;

@SuppressWarnings("uncommentedmain")
public class Task7 {
    private Task7() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        TreeMap<String, String> tree = new TreeMap<>(new MyComparator());
        tree.put("a", "a");
        tree.put("b", "b");
        tree.put(null, "n");
        tree.put("c", "c");
        LOGGER.info("");
    }
}
