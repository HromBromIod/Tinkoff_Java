package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task5.isCorrectCarNumber;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    private Task5Test() {
    }

    @Test
    @DisplayName("null test")
    void nullTest() {
        boolean actual = isCorrectCarNumber(null);
        assertFalse(actual);
    }

    @Test
    @DisplayName("empty test")
    void emptyTest() {
        boolean actual = isCorrectCarNumber("");
        assertFalse(actual);
    }

    @Test
    @DisplayName("incorrect car number test 1")
    void inCorrectPasswordTest1() {
        boolean actual = isCorrectCarNumber("Z345UJ21");
        assertFalse(actual);
    }

    @Test
    @DisplayName("incorrect car number test 2")
    void inCorrectPasswordTest2() {
        boolean actual = isCorrectCarNumber("A345BC2132");
        assertFalse(actual);
    }

    @Test
    @DisplayName("incorrect car number test 3")
    void inCorrectPasswordTest3() {
        boolean actual = isCorrectCarNumber("A3f5BC213");
        assertFalse(actual);
    }

    @Test
    @DisplayName("correct car number test 1")
    void correctPasswordTest1() {
        boolean actual = isCorrectCarNumber("A345BC122");
        assertTrue(actual);
    }

    @Test
    @DisplayName("correct car number test 2")
    void correctPasswordTest2() {
        boolean actual = isCorrectCarNumber("С654ВВ23");
        assertTrue(actual);
    }
}
