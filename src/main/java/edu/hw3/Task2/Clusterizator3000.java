package edu.hw3.Task2;

import java.util.ArrayList;
import java.util.Stack;

public class Clusterizator3000 {
    private Clusterizator3000() {
    }

    public static ArrayList<String> clusterize(String string) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) != '(' && string.charAt(i) != ')') {
                return result;
            }
        }
        String cut = string;
        for (int i = 1; !cut.equalsIgnoreCase(""); ++i) {
            String str = cutBalance(cut);
            if (str.equalsIgnoreCase("")) {
                cut = str;
            } else {
                result.add(str);
                cut = cut.substring(str.length());
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
