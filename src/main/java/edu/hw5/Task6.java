package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {
    private Task6() {
    }

    public static boolean isSubstring(String str, String subStr) {
        boolean result = false;
        if (str != null && subStr != null) {
            Pattern subStrPattern = Pattern.compile(Pattern.quote(subStr));
            Matcher strMatcher = subStrPattern.matcher(str);
            if (strMatcher.find()) {
                result = true;
            }
        }
        return result;
    }
}
