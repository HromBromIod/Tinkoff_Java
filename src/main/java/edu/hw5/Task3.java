package edu.hw5;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {
    private Task3() {
    }

    private static final String SPLITTER = ")|(";
    private static final String STRING_DATA_PATTERN_1 = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])";
    private static final String STRING_DATA_PATTERN_2 = "\\d{4}-(0[1-9]|1[0-2])-([1-9]|[1-2]\\d|3[0-1])";
    private static final String STRING_DATA_PATTERN_3 = "([1-9]|[1-2]\\d|3[0-1])/([1-9]|1[0-2])/\\d{4}";
    private static final String STRING_DATA_PATTERN_4 = "([1-9]|[1-2]\\d|3[0-1])/([1-9]|1[0-2])/\\d{2}";
    private static final String STRING_DATA_PATTERN_5 = "yesterday";
    private static final String STRING_DATA_PATTERN_6 = "today";
    private static final String STRING_DATA_PATTERN_7 = "tomorrow";
    private static final String STRING_DATA_PATTERN_8 = "(\\d+) days? ago";
    private static final String STRING_DATA_FULL_PATTERN =
        "^((" + STRING_DATA_PATTERN_1 + SPLITTER + STRING_DATA_PATTERN_2 + SPLITTER + STRING_DATA_PATTERN_3 + SPLITTER
            + STRING_DATA_PATTERN_4 + SPLITTER + STRING_DATA_PATTERN_5 + SPLITTER + STRING_DATA_PATTERN_6 + SPLITTER
            + STRING_DATA_PATTERN_7 + SPLITTER + STRING_DATA_PATTERN_8 + "))$";
    private static final Pattern DATA_PATTERN = Pattern.compile(STRING_DATA_FULL_PATTERN);

    public static Optional<LocalDate> parseDate(String dataStr) {
        LocalDate date = null;
        Matcher dataMatcher = DATA_PATTERN.matcher(dataStr);
        if (dataMatcher.find()) {
            if (dataStr.matches("^(" + STRING_DATA_PATTERN_1 + SPLITTER + STRING_DATA_PATTERN_2 + ")$")) {
                String[] args = dataStr.split("-");
                date = LocalDate.of(
                    Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[2])
                );
            }
            if (dataStr.matches("^" + STRING_DATA_PATTERN_3 + "$")) {
                String[] args = dataStr.split("/");
                date = LocalDate.of(
                    Integer.parseInt(args[2]),
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[0])
                );
            }
            if (dataStr.matches("^" + STRING_DATA_PATTERN_4 + "$")) {
                final int THIS_YEAR = 23;
                String[] args = dataStr.split("/");
                if (Integer.parseInt(args[2]) > THIS_YEAR) {
                    date = LocalDate.of(
                        Integer.parseInt("19" + args[2]),
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[0])
                    );
                } else {
                    date = LocalDate.of(
                        Integer.parseInt("20" + args[2]),
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[0])
                    );
                }
            }
            if (dataStr.matches("^" + STRING_DATA_PATTERN_5 + "$")) {
                date = LocalDate.now().minusDays(1);
            }
            if (dataStr.matches("^" + STRING_DATA_PATTERN_6 + "$")) {
                date = LocalDate.now();
            }
            if (dataStr.matches("^" + STRING_DATA_PATTERN_7 + "$")) {
                date = LocalDate.now().plusDays(1);
            }
            if (dataStr.matches("^" + STRING_DATA_PATTERN_8 + "$")) {
                date = LocalDate.now().minusDays(Integer.parseInt(dataStr.substring(0, dataStr.indexOf(" "))));
            }
            return Optional.of(date);
        }
        return Optional.empty();
    }
}
