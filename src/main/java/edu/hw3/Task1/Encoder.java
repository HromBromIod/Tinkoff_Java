package edu.hw3.Task1;

import java.util.HashMap;

public class Encoder {
    private static HashMap<Character, Character> hashMapCode;

    private Encoder() {
    }

    private static void initEncoder() {
        hashMapCode = new HashMap<>();
        for (char l = 'a', r = 'z'; l <= 'm'; ++l, --r) {
            hashMapCode.put(l, r);
            hashMapCode.put(r, l);
        }
        for (char l = 'A', r = 'Z'; l <= 'M'; ++l, --r) {
            hashMapCode.put(l, r);
            hashMapCode.put(r, l);
        }
    }

    public static String atbash(String str) {
        initEncoder();
        String string = str;
        for (int i = 0; i < string.length(); ++i) {
            if (hashMapCode.containsKey(string.charAt(i))) {
                string = string.substring(0, i) + hashMapCode.get(string.charAt(i))
                    + string.substring(i + 1);
            }
        }
        return string;
    }
}
