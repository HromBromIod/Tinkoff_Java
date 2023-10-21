package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Task2.Square()),
            Arguments.of(new Task2.Rectangle()),
            Arguments.of(new Task2.Square(10)),
            Arguments.of(new Task2.Rectangle(10, 10))
        };
    }

    static Arguments[] squares() {
        return new Arguments[] {
            Arguments.of(new Task2.Square()),
            Arguments.of(new Task2.Square(10)),
        };
    }

    @DisplayName("Default rectangle area test")
    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleAreaDefaultTest(Task2.Rectangle rect) {
        rect.setWidth(20);
        rect.setHeight(10);
        assertThat(rect.area()).isEqualTo(200.0);
    }

    @DisplayName("Person square test")
    @ParameterizedTest
    @MethodSource("squares")
    void rectangleAreaTest1(Task2.Square squa) {
        squa.setSide(30);
        assertThat(squa.area()).isEqualTo(900.0);
    }

}
