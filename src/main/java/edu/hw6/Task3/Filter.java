package edu.hw6.Task3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter {
    private Filter() {
    }

    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;

    public static final AbstractFilter READABLE = Files::isReadable;

    public static AbstractFilter largerThan(long expectedSize) {
        return (entry) -> Files.size(entry) > expectedSize;
    }

    public static AbstractFilter globMatches(String pattern) {
        return (entry) -> entry.toString().matches(pattern);
    }

    public static AbstractFilter magicNumber(char... chars) throws FileNotFoundException {
        final int charsCount = chars.length;
        return (entry) -> {
            char[] charsFromFile = new char[charsCount];
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(entry.toFile()))) {
                bufferedReader.read(charsFromFile, 0, charsCount);
            }
            return Arrays.equals(chars, charsFromFile);
        };
    }

    public static AbstractFilter regexContains(String stringPattern) {
        return (entry) -> {
            Matcher matcher = Pattern.compile(stringPattern).matcher(entry.getFileName().toString());
            return matcher.find();
        };
    }
}
