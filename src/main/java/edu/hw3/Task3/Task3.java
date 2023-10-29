package edu.hw3.Task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task3 {
    private Task3() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 1, 1));
        HashMap<?, Integer> hashMap = freqDict(list);
        LOGGER.info(hashMap.get(1));
    }

    public static HashMap<?, Integer> freqDict(List<?> list) {
        HashMap<Object, Integer> hashMap = new HashMap<>();
        for (Object o : list) {
            if (hashMap.containsKey(o)) {
                hashMap.replace(o, hashMap.get(o) + 1);
            } else {
                hashMap.put(o, 1);
            }
        }
        return hashMap;
    }
}
