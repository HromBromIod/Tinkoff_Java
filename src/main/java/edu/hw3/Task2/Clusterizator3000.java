package edu.hw3.Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Clusterizator3000 {
    private Clusterizator3000() {
    }

    public static List<String> clusterize(String string) throws IllegalArgumentException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) != '(' && string.charAt(i) != ')') {
                throw new IllegalArgumentException();
            }
        }
        String cut = string;
        while (!cut.isEmpty()) {
            String str = cutBalance(cut);
            if (str.isEmpty()) {
                return result;
            } else {
                result.add(str);
                cut = cut.substring(str.length());
            }
        }
        return result;
    }

    public static String cutBalance(String string) {
        String result = "";
        if (string.charAt(0) == ')') {
            return result;
        }
        Stack<Character> st = new Stack<>();
        st.push(string.charAt(0));
        int iterator;
        for (iterator = 1; !st.isEmpty() && iterator < string.length(); ++iterator) {
            char c = string.charAt(iterator);
            if (c == '(') {
                st.push(c);
            } else if (st.isEmpty()) {
                return result;
            } else {
                st.pop();
            }
        }
        if (st.isEmpty() && iterator <= string.length()) {
            result = string.substring(0, iterator);
        }
        return result;
    }
}
