package project1hangman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import org.apache.logging.log4j.LogManager;

@SuppressWarnings("RegexpSinglelineJava")
public class RandomWordSelector {
    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private String[] words = new String[0];

    public RandomWordSelector(final String fileName) {
        StringBuilder strBuild = new StringBuilder();
        try (BufferedReader buffRead = new BufferedReader(new FileReader(
            fileName))) {
            buffRead.lines().forEach(strBuild::append);
        } catch (FileNotFoundException exception) {
            System.out.print("\nFile not found!");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        String wordsSeparate = strBuild.toString();
        words = wordsSeparate.split(", ");
    }

    public String getRandomWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }
}
