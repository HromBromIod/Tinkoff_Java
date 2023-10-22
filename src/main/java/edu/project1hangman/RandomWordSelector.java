package edu.project1hangman;

import java.util.Random;
import java.io.BufferedReader;
import org.apache.logging.log4j.LogManager;

@SuppressWarnings("RegexpSinglelineJava")
public class RandomWordSelector {
    private String[] words;

    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    public RandomWordSelector(final String[] dictionary) {
        words = new String[dictionary.length];
        for (int i = 0; i < dictionary.length; ++i)
            words[i] = dictionary[i];
    }

    public String getRandomWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }
}
