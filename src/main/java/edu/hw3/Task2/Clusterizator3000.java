package edu.hw3.Task2;

import java.util.ArrayList;
import java.util.Stack;

public class Clusterizator3000 {
    private Clusterizator3000() {
    }

    public static ArrayList<String> clusterize(String string) {
        ArrayList<String> result = new ArrayList<>();
        boolean flagOfOut = false;
        for (int i = 0; i < string.length() && !flagOfOut; ++i) {
            if (string.charAt(i) != '(' && string.charAt(i) != ')') {
                flagOfOut = true;
            }
        }
        if (flagOfOut) {
            return result;
        }
        for (int i = 1; !string.equalsIgnoreCase(""); ++i) {
            String str = cutBalance(string);
            if (str.equalsIgnoreCase("")) {
                string = str;
            } else {
                result.add(str);
                string = string.substring(str.length(), string.length());
            }
        }
        return result;
    }

    public static String cutBalance(String string) {
        String result = "";
        Stack<Character> st = new Stack<>();
        st.push(string.charAt(0));
        int iterator = 1;
        while (!st.isEmpty() && iterator < string.length()) {
            char c = string.charAt(iterator);
            if (c == ')' && c != st.peek()) {
                st.pop();
            } else {
                st.push(c);
            }
            iterator += 1;
        }
        if (st.isEmpty() && iterator <= string.length()) {
            result = string.substring(0, iterator);
        }
        return result;
    }
}
