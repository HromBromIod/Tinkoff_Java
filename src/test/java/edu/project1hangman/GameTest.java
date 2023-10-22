package edu.project1hangman;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.project1hangman.GameInitialization;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    @DisplayName("Пустая строка вместо слова (исходный словарь пуст)")
    void emptyWordTest() {
        GameInitialization initialization =
            new GameInitialization(new String[]{""}, "N\nE\n", 5);
        initialization.startGame();
        String actual = initialization.getPrintStream().substring(28, 71);
        String expected = "\n>A word has been guessed, but it's empty.\n";
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Пример выигрыша")
    void winTest() {
        GameInitialization initialization =
            new GameInitialization(new String[]{"casual"}, "N\nc\na\ns\nu\nl\nE\n", 5);
        initialization.startGame();
        String stream = initialization.getPrintStream();
        String actual = stream.substring(stream.length() - 38, stream.length() - 28);
        String expected = "\n>You won!";
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Пример проигрыша")
    void loseTest() {
        GameInitialization initialization =
            new GameInitialization(new String[]{"extreme"}, "N\nc\na\ns\nu\nl\nE\n", 5);
        initialization.startGame();
        String stream = initialization.getPrintStream();
        String actual = stream.substring(stream.length() - 39, stream.length() - 28);
        String expected = "\n>You lost!";
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Проверка на возможность сдаться")
    void giveUp() {
        GameInitialization initialization =
            new GameInitialization(new String[]{"mouse"}, "N\nGive Up\nE\n", 5);
        initialization.startGame();
        String stream = initialization.getPrintStream();
        String actual = stream.substring(175, 189);
        String expected = "\n>You give up!";
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Проверка на изменение данных при выоборе верной буквы")
    void hitTest() {
        GameInitialization initialization =
            new GameInitialization(new String[]{"print"}, "N\np\nr\ni\nn\nt\nE\n", 5);
        initialization.startGame();
        String stream = initialization.getPrintStream();
        String actual = stream.substring(133, 139);
        String expected = ">Hit!\n";
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Проверка на отсутствие изменения данных при выоборе неверной буквы")
    void missTest() {
        GameInitialization initialization =
            new GameInitialization(new String[]{"print"}, "N\nk\np\nr\ni\nn\nt\nE\n", 5);
        initialization.startGame();
        String stream = initialization.getPrintStream();
        String actual = stream.substring(133, 162);
        String expected = ">Missed, mistake 1 out of 5.\n";
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Проверка на повторный выбор буквы, если была введена строка")
    void moreThenOneLetter() {
        GameInitialization initialization =
            new GameInitialization(new String[]{"last"}, "N\nken\nl\na\ns\nt\nE\n", 5);
        initialization.startGame();
        String stream = initialization.getPrintStream();
        String actual = stream.substring(132, 174);
        String expected = ">Incorrect input!\n>Input correct letter:\n<";
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Проверка на повторный выбор пункта меню при неверном наборе")
    void wrongChoice() {
        GameInitialization initialization =
            new GameInitialization(new String[]{"ghost"}, "t\nN\ng\nh\no\ns\nt\nE\n", 5);
        initialization.startGame();
        String stream = initialization.getPrintStream();
        String actual = stream.substring(28, 62);
        String expected = ">Incorrect input!\n>Repeat input:\n<";
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Проверка на повторный ввод букв")
    void doubleLetter() {
        GameInitialization initialization =
            new GameInitialization(new String[]{"twice"}, "N\nt\nt\nw\ni\nc\ne\nE\n", 5);
        initialization.startGame();
        String stream = initialization.getPrintStream();
        String actual = stream.substring(177, 210);
        String expected = "\n>This letter is already by used!";
        assertEquals(actual, expected);
    }
}
