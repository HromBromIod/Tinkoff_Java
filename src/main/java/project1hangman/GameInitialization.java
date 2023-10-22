package project1hangman;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;

//new InputStreamReader(System.in)

@SuppressWarnings("RegexpSinglelineJava") public class GameInitialization {
    final static int COUNT_OF_MISTAKES = 0;
    final static int MAX_COUNT_OF_MISTAKES = 5;
    private final RandomWordSelector wordSelector;
    private final Word wordOperator;
    int countOfMistakes;
    private String letter;
    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    GameInitialization(String fileName) {
        this.wordSelector = new RandomWordSelector(fileName);
        this.wordOperator = new Word();
        this.countOfMistakes = 0;
    }

    private void inputLetterOrGiveUp() {
        Scanner scanner = new Scanner(System.in);
        boolean flagOfOut = false;
        while (!flagOfOut) {
            letter = scanner.nextLine();
            if (letter.length() == 1 && Character.isLetter(letter.charAt(0))) {
                flagOfOut = true;
            } else {
                if (letter.equals("give up")) {
                    countOfMistakes = COUNT_OF_MISTAKES;
                    letter = " ";
                    flagOfOut = true;
                }
                System.out.print("\n>Input correct letter:<");
            }
        }
    }

    private String inputChoice() {
        Scanner scanner = new Scanner(System.in);
        String result;
        do {
            result = scanner.nextLine();
            if (result.length() == 1 && (result.equals("N") || result.equals("E"))) {
                return result;
            } else {
                System.out.print(">Incorrect input!\n>Repeat input:\n<");
            }
        } while (true);
    }

    private String inputLetter() {
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        boolean flagOfOut = false;
        while (!flagOfOut) {
            if (result.length() == 1 && Character.isLetter(letter.charAt(0))) {
                flagOfOut = true;
            } else {
                System.out.print(">Incorrect input!\n>Repeat input:\n<");
                result = scanner.nextLine();
            }
        }
        return result;
    }

    public void startGame(BufferedInputStream bis, BufferedOutputStream bos) {
        LOGGER.info("hello");
    }




    public void start() {
        Scanner scanner = new Scanner(System.in);
        String choice = "N";
        boolean flagOfOut = false;
        while (!flagOfOut) {
            System.out.print("\n>Menu: [N]ew game/ [E]xit\n<");
            choice = inputChoice();
            if (choice.equalsIgnoreCase("N")) {
                countOfMistakes = 0;
                wordOperator.clearBuffer();
                String guessedWord = wordSelector.getRandomWord();
                wordOperator.setWord(guessedWord);
                System.out.print(">A word has been guessed!\n>If you want to give up, enter - give up\n");
                while (!wordOperator.isWin() && !flagOfOut) {
                    System.out.print("\n>Guess a letter: \n<");
                    inputLetterOrGiveUp();
                    //исправить
                    if (countOfMistakes == COUNT_OF_MISTAKES) {
                        System.out.print("\n>You give up!");
                        flagOfOut = true;
                    }
                    if (wordOperator.isLetterWasUsed(letter)) {
                        System.out.print("\n>This letter is already by used!");
                    } else {
                        wordOperator.inputLetterInSet(letter);
                        if (wordOperator.checkLetterInSet(letter)) {
                            System.out.print(">Hit!\n");
                            wordOperator.updateMask(letter);
                            wordOperator.printMask();
                        } else {
                            countOfMistakes++;
                            System.out.print(
                                ">Missed, mistake " + countOfMistakes + " out of " + MAX_COUNT_OF_MISTAKES + ".\n");
                            wordOperator.printMask();

                        }
                    }
                    if (countOfMistakes == MAX_COUNT_OF_MISTAKES) {
                        System.out.print("\n>You lost!");
                        break;
                    }
                }
                if (wordOperator.isWin()) {
                    System.out.print("\n>You won!");
                }
            } else {
                System.exit(0);
            }
        }
    }
}
