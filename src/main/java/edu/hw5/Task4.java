package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {
    private Task4() {
    }

    private static final Pattern PASSWORD = Pattern.compile("[~!@#$%^&*|]");

    public static boolean isCorrectPassword(String password) {
        boolean result = false;
        if (password != null) {
            Matcher matcher = PASSWORD.matcher(password);
            if (matcher.find()) {
                result = true;
            }
        }
        return result;
    }
}
