package edu.project1hangman;

@SuppressWarnings("uncommentedmain")
public class StartGame {
    private static final int FIVE = 5;

    private StartGame() {
    }

    public static void main(String[] args) {
        GameInitialization initialization = new GameInitialization(new String[] {"java", "casual", "enum"}, FIVE);
        initialization.startGame();
    }
}
