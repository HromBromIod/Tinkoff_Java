package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task5 {
    private Task5() {
    }

    private static final Pattern CAR_NUMBER =
        Pattern.compile("^[АAВBЕEКKМMНHОOРPСCТTУYХX]\\d{3}[АAВBЕEКKМMНHОOРPСCТTУYХX]{2}\\d{2,3}$");

    public static boolean isCorrectCarNumber(String carNumber) {
        boolean result = false;
        if (carNumber != null) {
            Matcher matcher = CAR_NUMBER.matcher(carNumber);
            if (matcher.find()) {
                result = true;
            }
        }
        return result;
    }
}
