package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task8 {
    private Task8() {
    }

    //нечетной длины
    public static final Pattern STRING_PATTERN_1 = Pattern.compile("^[01]([01]{2})*$");
    //начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
    public static final Pattern STRING_PATTERN_2 = Pattern.compile("(^0([01]{2})*$)|(^(?=1)([01]{2})+$)");
    //каждый нечетный символ равен 1
    public static final Pattern STRING_PATTERN_5 = Pattern.compile("^(1([01]?))+$");

    public static boolean isValidString(String str, Pattern strPattern) {
        if (str != null && !str.isEmpty()) {
            Matcher strMatcher = strPattern.matcher(str);
            return strMatcher.find();
        }
        return false;
    }
}
