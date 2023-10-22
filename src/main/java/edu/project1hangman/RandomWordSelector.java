package edu.project1hangman;

import java.util.Random;
import org.apache.logging.log4j.LogManager;

@SuppressWarnings("RegexpSinglelineJava")
public class RandomWordSelector {
    private String[] words;

    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    public RandomWordSelector(final String[] dictionary) {
        words = dictionary;
    }

    public String getRandomWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }
}
