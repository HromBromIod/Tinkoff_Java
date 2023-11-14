package edu.hw3.Task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("uncommentedmain")
public class MyGenericForFrecDict {
    private MyGenericForFrecDict() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> list) {
        Map<T, Integer> hashMap = new HashMap<>();
        for (T o : list) {
            if (hashMap.containsKey(o)) {
                hashMap.put(o, hashMap.get(o) + 1);
            } else {
                hashMap.put(o, 1);
            }
        }
        return hashMap;
    }
}
