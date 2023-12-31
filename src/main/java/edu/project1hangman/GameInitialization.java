package edu.project1hangman;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

@SuppressWarnings("RegexpSinglelineJava")
public class GameInitialization {
    private static int maxCountOfMistakes;
    private final RandomWordSelector wordSelector;
    private final Word wordOperator;
    int countOfMistakes;
    private String letter;
    private final PrintStream out;
    private ByteArrayOutputStream stream;
    private final Scanner scanner;

    public GameInitialization(final String[] dictionary, int count) {
        scanner = new Scanner(System.in);
        out = new PrintStream(System.out);
        wordSelector = new RandomWordSelector(dictionary);
        wordOperator = new Word();
        maxCountOfMistakes = count;
        countOfMistakes = 0;
    }

    public GameInitialization(final String[] dictionary, String in, int count) {
        scanner = new Scanner(in);
        stream = new ByteArrayOutputStream();
        out = new PrintStream(stream);
        wordSelector = new RandomWordSelector(dictionary);
        wordOperator = new Word();
        maxCountOfMistakes = count;
        countOfMistakes = 0;
    }

    public String getPrintStream() {
        return stream.toString();
    }

    private void inputLetterOrGiveUp() {
        boolean flagOfOut = false;
        while (!flagOfOut) {
            letter = scanner.nextLine();
            if (letter.length() == 1 && Character.isLetter(letter.charAt(0))) {
                flagOfOut = true;
            } else {
                if (letter.equals("Give Up")) {
                    countOfMistakes = -1;
                    letter = " ";
                    flagOfOut = true;
                }
                System.out.print(">Incorrect input!\n>Input correct letter:\n<");
            }
        }
    }

    private String inputChoice() {
        String result = "";
        boolean flagOfOut = false;
        while (!flagOfOut) {
            result = scanner.nextLine();
            if (result.length() == 1 && (result.equals("N") || result.equals("E"))) {
                flagOfOut = true;
            } else {
                System.out.print(">Incorrect input!\n>Repeat input:\n<");
            }
        }
        return result;
    }

    public void startGame() {
        System.setOut(out);
        String choice;
        while (true) {
            System.out.print("\n>Menu: [N]ew game/ [E]xit\n<");
            choice = inputChoice();
            if (choice.equalsIgnoreCase("E")) {
                break;
            }
            boolean flagOfLost = false;
            countOfMistakes = 0;
            wordOperator.clearBuffer();
            wordOperator.setWord(wordSelector.getRandomWord());
            if (!wordOperator.isEmpty()) {
                System.out.print(">A word has been guessed!\n>If you want to give up, enter - Give Up\n");
                while (!wordOperator.isWin() && !flagOfLost) {
                    if (countOfMistakes == -1) {
                        System.out.print("\n>You give up!");
                        break;
                    }
                    if (countOfMistakes == maxCountOfMistakes) {
                        System.out.print("\n>You lost!");
                        break;
                    }
                    wordOperator.printMask();
                    System.out.print("\n>Guess a letter: \n<");
                    inputLetterOrGiveUp();
                    if (letter.equalsIgnoreCase(" ")) {
                        countOfMistakes = -1;
                        continue;
                    }
                    if (wordOperator.isLetterWasUsed(letter)) {
                        System.out.print("\n>This letter is already by used!");
                        continue;
                    }
                    wordOperator.inputLetterInSet(letter);
                    if (wordOperator.checkLetterInWord(letter)) {
                        System.out.print(">Hit!\n");
                        wordOperator.updateMask(letter);
                    } else {
                        countOfMistakes++;
                        System.out.print(
                            ">Missed, mistake " + countOfMistakes + " out of " + maxCountOfMistakes
                                + ".\n");
                    }
                }
                if (wordOperator.isWin()) {
                    System.out.print("\n>You won!");
                }
            } else {
                System.out.print("\n>A word has been guessed, but it's empty.\n");
            }
        }
    }
}
