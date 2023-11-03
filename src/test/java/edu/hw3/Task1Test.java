package edu.hw3;

import edu.hw3.Task1.Encoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    private Task1Test() {
    }

    @DisplayName("Some encoder tests")
    @ParameterizedTest
    @CsvSource({"Hello world!, Svool dliow!",
        "Did you know who I am?, Wrw blf pmld dsl R zn?",
        "R'n Yzgnzm!, I'm Batman!"})
    void encoderTest(String source, String expected) {
        Encoder encoder = new Encoder();
        String actual = encoder.atbash(source);
        assertEquals(actual, expected);
    }
}
