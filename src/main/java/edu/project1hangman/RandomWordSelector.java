package edu.project1hangman;

import java.util.Random;

@SuppressWarnings("RegexpSinglelineJava")
public class RandomWordSelector {
    private String[] words;

    public RandomWordSelector(final String[] dictionary) {
        words = dictionary;
    }

    public String getRandomWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }
}
