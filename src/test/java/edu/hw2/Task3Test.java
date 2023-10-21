package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    @DisplayName("Check some commands")
    @ParameterizedTest()
    @CsvSource({
        "apt download hello.exe && apt execute hello.exe && apt delete hello.exe",
        "apt execute goodbye.exe"
    })
    void check(String command) {
        boolean actual = false;
        boolean expected = true;
        try {
            Task3.PopularCommandExecutor exe = new Task3.PopularCommandExecutor();
            exe.tryExecute(command);
            actual = true;
        } catch (RuntimeException exception) {
            expected = false;
        }
        assertEquals(expected, actual);
    }

    @DisplayName("Check update")
    @RepeatedTest(10)
    void checkUpdate() {
        boolean actual = false;
        boolean expected = true;
        try {
            Task3.PopularCommandExecutor exe = new Task3.PopularCommandExecutor();
            exe.updatePackages();
            actual = true;
        } catch (RuntimeException exception) {
            expected = false;
        }
        assertEquals(expected, actual);
    }
}
