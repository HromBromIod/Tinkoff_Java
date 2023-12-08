package edu.hw5;

import static edu.hw5.Task1.averageSessionTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    private Task1Test() {
    }

    @Test
    @DisplayName("null list test")
    void nullListTest() {
        assertThrows(IllegalArgumentException.class, () -> averageSessionTime(null));
    }

    @Test
    @DisplayName("empty list test")
    void emptyListTest() {
        assertThrows(IllegalArgumentException.class, () -> averageSessionTime(List.of()));
    }

    @Test
    @DisplayName("invalid dats in list test")
    void invalidDatsInListTest() {
        assertThrows(RuntimeException.class, () -> averageSessionTime(List.of(
            "2022-03-12, 20:20 - 2022-43-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        )));
    }

    @Test
    @DisplayName("valid list test")
    void validDatsInListTest() {
        String expected = "3ч 40м";
        String actual = averageSessionTime(List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        ));
        assertEquals(expected, actual);
    }
}
