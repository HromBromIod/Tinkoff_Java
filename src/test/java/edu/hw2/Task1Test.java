package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class Task1Test {

    @ParameterizedTest(name = "Check Constant")
    @CsvSource({
        "1, 1",
        "-1, -1",
        "0, 0"
    })
    void checkConstantTest(double actual, double expected) {
        assertEquals(expected, new Task1.Expr.Constant(actual).evaluate());
    }

    @ParameterizedTest(name = "Check Negate")
    @CsvSource({
        "1, -1",
        "-1, 1",
        "0, 0"
    })
    void checkNegateTest(double actual, double expected) {
        assertEquals(expected, new Task1.Expr.Negate(new Task1.Expr.Constant(actual)).evaluate());
    }

    @ParameterizedTest(name = "Check Exponent")
    @CsvSource({
        "1, 2, 1",
        "1, 0, 1",
        "-1, 2, 1",
        "-1, 3, -1",
        "-1, 0, 1",
        "0, 0, 1",
        "0, 2, 0"
    })
    void checkExponentTest(double valueLeft, int degree, double expected) {
        double actual = new Task1.Expr.Exponent(new Task1.Expr.Constant(valueLeft), degree).evaluate();
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Check Addition")
    @CsvSource({
        "1, 2, 3",
        "1, -1, 0",
        "0, 0, 0",
        "-2, 1, -1",
        "0.5, 0.9, 1.4"
    })
    void checkAdditionTest(double valueLeft, double valueRight, double expected) {
        double actual =
            new Task1.Expr.Addition(new Task1.Expr.Constant(valueLeft), new Task1.Expr.Constant(valueRight)).evaluate();
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Check Multiplication")
    @CsvSource({
        "1, 2, 2",
        "1, 0, 0",
        "1, -1, -1",
        "-1, 0, 0"
    })
    void checkMultiplicationTest(double valueLeft, double valueRight, double expected) {
        double actual = new Task1.Expr.Multiplication(
            new Task1.Expr.Constant(valueLeft),
            new Task1.Expr.Constant(valueRight)
        ).evaluate();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("MultiCheck")
    void multiCheckTest() {
        Task1.Expr actual = new Task1.Expr.Addition(
            new Task1.Expr.Exponent(new Task1.Expr.Multiplication(new Task1.Expr.Addition(
                new Task1.Expr.Constant(2),
                new Task1.Expr.Constant(4)
            ), new Task1.Expr.Negate(new Task1.Expr.Constant(1))), 2),
            new Task1.Expr.Constant(1)
        );
        double expected = 37;
        assertEquals(expected, actual.evaluate());
    }
}
