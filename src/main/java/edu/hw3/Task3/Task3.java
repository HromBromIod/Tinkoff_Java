package edu.hw3.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

@SuppressWarnings("uncommentedmain")
public class Task3 {
    private Task3() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 1, 3));
        HashMap<?, Integer> hashMap = freqDict(list);
        LOGGER.info(hashMap.get(1));
    }

    public static HashMap<?, Integer> freqDict(List<?> list) {
        HashMap<Object, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < list.size(); ++i) {
            if (hashMap.containsKey(list.get(i))) {
                hashMap.replace(list.get(i), hashMap.get(list.get(i)) + 1);
            } else {
                hashMap.put(list.get(i), 1);
            }
        }
        return hashMap;
    }
}
