package edu.hw2;

import edu.hw2.task2.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Square()),
            Arguments.of(new Rectangle()),
            Arguments.of(new Square(10)),
            Arguments.of(new Rectangle(10, 10))
        };
    }

    static Arguments[] squares() {
        return new Arguments[] {
            Arguments.of(new Square()),
            Arguments.of(new Square(10)),
        };
    }

    @DisplayName("Default rectangle area test")
    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleAreaDefaultTest(Rectangle rect) {
        rect.setWidth(20);
        rect.setHeight(10);
        assertThat(rect.area()).isEqualTo(200.0);
    }

    @DisplayName("Person square test")
    @ParameterizedTest
    @MethodSource("squares")
    void rectangleAreaTest1(Square squa) {
        squa.setSide(30);
        assertThat(squa.area()).isEqualTo(900.0);
    }

}
