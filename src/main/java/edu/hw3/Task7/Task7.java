package edu.hw3.Task7;

import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task7 {
    private Task7() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        TreeMap<String, String> myTree = new TreeMap<>(new MyComparator());
        myTree.put("a", "a");
        myTree.put("b", "b");
        myTree.put(null, "n");
        myTree.put("c", "c");
        LOGGER.info("");
    }
}
