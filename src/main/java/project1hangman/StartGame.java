package project1hangman;

@SuppressWarnings("uncommentedmain")
public class StartGame {
    private StartGame() {
    }

    public static void main(String[] args) {
        GameInitialization init = new GameInitialization("src\\main\\java\\edu\\project1\\words.txt");
        init.start();
    }
}
