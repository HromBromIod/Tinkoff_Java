package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static edu.hw5.Task3.parseDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    @Test
    @DisplayName("2020-10-10 test")
    void validStringTest1() {
        Optional<LocalDate> actual = parseDate("2020-10-10");
        Optional<LocalDate> expected = Optional.of(LocalDate.of(2020, 10, 10));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2020-10-2 test")
    void validStringTest2() {
        Optional<LocalDate> actual = parseDate("2020-10-2");
        Optional<LocalDate> expected = Optional.of(LocalDate.of(2020, 10, 2));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1/3/1976 test")
    void validStringTest3() {
        Optional<LocalDate> actual = parseDate("1/3/1976");
        Optional<LocalDate> expected = Optional.of(LocalDate.of(1976, 3, 1));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1/3/20 test")
    void validStringTest4() {
        Optional<LocalDate> actual = parseDate("1/3/20");
        Optional<LocalDate> expected = Optional.of(LocalDate.of(2020, 3, 1));
        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("yesterday test")
    void validStringTest5() {
        Optional<LocalDate> actual = parseDate("yesterday");
        Optional<LocalDate> expected = Optional.of(LocalDate.now().minusDays(1));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("today test")
    void validStringTest6() {
        Optional<LocalDate> actual = parseDate("today");
        Optional<LocalDate> expected = Optional.of(LocalDate.now());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("tomorrow test")
    void validStringTest7() {
        Optional<LocalDate> actual = parseDate("tomorrow");
        Optional<LocalDate> expected = Optional.of(LocalDate.now().plusDays(1));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1 day ago test")
    void validStringTest8() {
        Optional<LocalDate> actual = parseDate("1 day ago");
        Optional<LocalDate> expected = Optional.of(LocalDate.now().minusDays(1));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("invalid string test")
    void invalidStringTest() {
        Optional<LocalDate> actual = parseDate("18241804");
        Optional<LocalDate> expected = Optional.empty();
        assertEquals(expected, actual);
    }
}
