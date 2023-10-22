package edu.project1hangman;

import edu.project1hangman.GameInitialization;
import org.apache.logging.log4j.LogManager;
import java.io.*;

@SuppressWarnings("uncommentedmain")
public class StartGame {
    private StartGame() {
    }
    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        String in = "N\nj\nGive Up\nj\nE";
        PrintStream console = new PrintStream(System.out);
        GameInitialization initialization = new GameInitialization(new String[]{"java, casual, enum"}, in, 5);
        //GameInitialization initialization = new GameInitialization("src\\main\\java\\project1hangman\\words.txt");
        initialization.startGame();
        System.setOut(console);
        in = initialization.getPrintStream();
        System.out.print(in);
    }
}
