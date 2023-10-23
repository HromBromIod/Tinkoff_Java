package edu.hw3.Task1;

import java.util.HashMap;

public class Encoder {
    private static HashMap<Character, Character> hashMap;
    public Encoder() {
        hashMap = new HashMap<>();
        for (char l = 'a', r = 'z'; l <= 'm' && 'n' <= r; ++l, --r) {
            hashMap.put(l, r);
            hashMap.put(r, l);
        }
        for (char L = 'A', R = 'Z'; L <= 'M' && 'N' <= R; ++L, --R) {
            hashMap.put(L, R);
            hashMap.put(R, L);
        }
    }

    public static String atbash(String string) {
        for (int i = 0; i < string.length(); ++i) {
            if (hashMap.containsKey(string.charAt(i))) {
                string = string.substring(0, i) + hashMap.get(string.charAt(i)) + string.substring(i + 1, string.length());
            }
        }
        return string;
    }
}
