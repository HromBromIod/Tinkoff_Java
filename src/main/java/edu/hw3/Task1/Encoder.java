package edu.hw3.Task1;

import java.util.HashMap;

public class Encoder {
    private final HashMap<Character, Character> hashMapCode;

    public Encoder() {
        hashMapCode = new HashMap<>();
        for (char l = 'a', r = 'z'; l <= r; ++l, --r) {
            hashMapCode.put(l, r);
            hashMapCode.put(r, l);
        }
        for (char l = 'A', r = 'Z'; l <= r; ++l, --r) {
            hashMapCode.put(l, r);
            hashMapCode.put(r, l);
        }
    }

    public String atbash(String str) {
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
