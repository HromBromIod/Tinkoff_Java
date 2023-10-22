package edu.project1hangman;

import java.io.*;
import org.apache.logging.log4j.LogManager;

@SuppressWarnings("uncommentedmain")
public class StartGame {
    private static final int FIVE = 5;
    private StartGame() {
    }

    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        String in = "N\nj\nGive Up\nj\nE";
        PrintStream console = new PrintStream(System.out);
        GameInitialization initialization = new GameInitialization(new String[] {"java, casual, enum"}, in, FIVE);
        //GameInitialization initialization = new GameInitialization("src\\main\\java\\project1hangman\\words.txt");
        initialization.startGame();
        System.setOut(console);
        in = initialization.getPrintStream();
        System.out.print(in);
    }
}
