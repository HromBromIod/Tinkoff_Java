package project1hangman;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.LogManager;

@SuppressWarnings("RegexpSinglelineJava")
public class Word {
    private String word;
    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private String mask;
    private int numberGuessletter = 0;
    private final Set<String> wordUniqueLetters = new HashSet<>();
    private final Set<String> usedLetters = new HashSet<>();

    public Set<String> getWordUniqueLetters() {
        return wordUniqueLetters;
    }

    public int getNumberGuessletter() {
        return 0;
    }

    public void setWord(String word) {
        this.word = word;
        StringBuilder strBuild = new StringBuilder();
        for (int i = 0; i < word.length(); ++i) {
            strBuild.append('*');
        }
        this.mask = strBuild.toString();
        Collections.addAll(wordUniqueLetters, word.split(""));
    }

    public void printMask() {
        System.out.print("\n>The word: " + mask + "\n");
    }

    public void updateMask(String letter) {
        for (int i = 0; i < word.length(); ++i) {
            if (Character.toString(word.charAt(i)).equalsIgnoreCase(letter)) {
                mask = mask.substring(0, i) + word.charAt(i) + mask.substring(i + 1);
            }
        }
    }

    public boolean isWin() {
        return word.equalsIgnoreCase(mask);
    }
    public void inputLetterInSet(String letter) {
        usedLetters.add(letter);
    }

    public boolean checkLetterInSet(String letter) {
        return wordUniqueLetters.contains(letter);
    }

    public boolean isLetterWasUsed(String letter) {
        return usedLetters.contains(letter);
    }

    public void clearBuffer() {
        usedLetters.clear();
        wordUniqueLetters.clear();
    }
}
