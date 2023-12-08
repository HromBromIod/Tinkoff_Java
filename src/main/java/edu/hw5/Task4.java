package edu.hw5;

import java.util.regex.Pattern;

public class Task4 {
    private Task4() {
    }

    private static final String PASSWORD = "\\w*[~!@#$%^&*|]\\w*";

    public static boolean isCorrectPassword(String password) {
        boolean result = false;
        if (password != null) {
            result = Pattern.compile(PASSWORD).matcher(password).matches();
        }
        return result;
    }
}
