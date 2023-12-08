package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task7 {
    private Task7() {
    }

    public static final Pattern STRING_PATTERN_1 = Pattern.compile("^[01]{2}0[01]*$");

    public static final Pattern STRING_PATTERN_2 = Pattern.compile("^(((0[01]*0)|0)|((1[01]*1)|1))$");

    public static final Pattern STRING_PATTERN_3 = Pattern.compile("^[01]{1,3}$");

    public static boolean isCorrectString(String str, Pattern pattern) {
        boolean result = false;
        if (str != null && pattern != null) {
            Matcher strMatcher = pattern.matcher(str);
            if (strMatcher.find()) {
                result = true;
            }
        }
        return result;
    }
}
