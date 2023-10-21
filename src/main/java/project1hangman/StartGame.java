package project1hangman;

@SuppressWarnings("uncommentedmain")
public class StartGame {
    private StartGame() {
    }

    public static void main(String[] args) {
        Game initialization = new Game();
        initialization.start();
    }
}
