package edu.hw3.Task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import static edu.hw3.Task2.Clusterizator3000.clusterize;

public class Task2 {
    private Task2() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        ArrayList<String> clusters = clusterize("((()))()((()))((()))");
        for (int i = 0; i < clusters.size(); ++i) {
            LOGGER.info(clusters.get(i));
        }
    }
}
