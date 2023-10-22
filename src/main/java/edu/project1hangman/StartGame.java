package edu.project1hangman;

import org.apache.logging.log4j.LogManager;

@SuppressWarnings("uncommentedmain")
public class StartGame {
    private static final int FIVE = 5;

    private StartGame() {
    }

    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        GameInitialization initialization = new GameInitialization(new String[] {"java", "casual", "enum"}, FIVE);
        initialization.startGame();
    }
}
