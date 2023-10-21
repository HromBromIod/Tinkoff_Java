package project1hangman;

import java.util.Scanner;
import java.util.Set;
import org.apache.logging.log4j.LogManager;

@SuppressWarnings("RegexpSinglelineJava")
public class Game {
    final static int WRONG_COUNT_MISTAKES = 0;
    final static int MAX_COUNT_MISTAKES = 5;
    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private final RandomWordSelector wordSelector = new RandomWordSelector("src\\main\\java\\edu\\project1\\words.txt");
    private final Word maskOperator = new Word();
    int countOfMistakes;
    private String letter;

    private void inputLetterOrGiveUp() {
        Scanner scanner = new Scanner(System.in);
        boolean flagOfOut = true;
        while (flagOfOut) {
            letter = scanner.nextLine();
            if (letter.length() == 1 && letter.charAt(0) >= 'a' && letter.charAt(0) <= 'z' &&
                Character.isLetter(letter.charAt(0))) {
                flagOfOut = false;
            } else {
                if (letter.equals("give up")) {
                    countOfMistakes = WRONG_COUNT_MISTAKES;
                    letter = " ";
                    flagOfOut = false;
                }
                System.out.print("\n>Input correct letter:<");
            }
        }
    }

    private String input_vubor() {
        Scanner scanner = new Scanner(System.in);
        String result;
        do {
            result = scanner.nextLine();
            if (result.length() == 1 && (result.equals("N") || result.equals("E"))) {
                return result;
            } else {
                System.out.print(">Input correct vubor:\n<");
            }
        } while (true);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String choice = "N";
        boolean flag;
        while (true) {
            if (choice.equalsIgnoreCase("N")) {
                countOfMistakes = 0;
                flag = false;
                maskOperator.clearBuffer();
                String guessedWord = wordSelector.getRandomWord();
                maskOperator.setWord(guessedWord);
                System.out.print(">A word has been guessed!\n>If you want to give up, enter - give up\n");
                while (!maskOperator.isWin()) {
                    System.out.print("\n>Guess a letter: \n<");
                    inputLetterOrGiveUp();
                    if (countOfMistakes == WRONG_COUNT_MISTAKES) {
                        System.out.print("\n>You give up!");
                        flag = true;
                        break;
                    }
                    if (maskOperator.isLetterWasUsed(letter)) {
                        System.out.print("\n>This letter is already by used!");
                    } else {
                        maskOperator.inputLetterInSet(letter);
                        if (maskOperator.checkLetterInSet(letter)) {
                            System.out.print(">Hit!\n");
                            maskOperator.updateMask(letter);
                            maskOperator.printMask();
                        } else {
                            countOfMistakes++;
                            System.out.print(">Missed, mistake " + countOfMistakes + " out of "
                                + MAX_COUNT_MISTAKES + ".\n");
                            maskOperator.printMask();

                        }
                    }
                    if (countOfMistakes == MAX_COUNT_MISTAKES) {
                        System.out.print("\n>You lost!");
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.print("\n>You won!");
                }
            } else {
                System.exit(0);
            }
            System.out.print("\n>Menu: [N]ew game/ [E]xit\n<");
            choice = input_vubor();
        }
    }
}
