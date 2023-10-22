package edu.hw2;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @ParameterizedTest(name = "Check Constant")
    @CsvSource({
        "1, 1",
        "-1, -1",
        "0, 0"
    })
    void checkConstantTest(double actual, double expected) {
        assertEquals(expected, new Expr.Constant(actual).evaluate());
    }

    @ParameterizedTest(name = "Check Negate")
    @CsvSource({
        "1, -1",
        "-1, 1",
        "0, 0"
    })
    void checkNegateTest(double actual, double expected) {
        assertEquals(expected, new Expr.Negate(new Expr.Constant(actual)).evaluate());
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
        double actual = new Expr.Exponent(new Expr.Constant(valueLeft), degree).evaluate();
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
            new Expr.Addition(new Expr.Constant(valueLeft), new Expr.Constant(valueRight)).evaluate();
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
        double actual = new Expr.Multiplication(
            new Expr.Constant(valueLeft),
            new Expr.Constant(valueRight)
        ).evaluate();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("MultiCheck1")
    void multiCheckTest1() {
        Expr actual = new Expr.Addition(
            new Expr.Exponent(new Expr.Multiplication(new Expr.Addition(
                new Expr.Constant(2),
                new Expr.Constant(4)
            ), new Expr.Negate(new Expr.Constant(1))), 2),
            new Expr.Constant(1)
        );
        double expected = 37;
        assertEquals(expected, actual.evaluate());
    }

    @Test
    @DisplayName("MultiCheck2")
    void multiCheckTest2() {
        var three = new Expr.Constant(3);
        var five = new Expr.Constant(5);
        var eight = new Expr.Constant(8);
        var negTwo = new Expr.Negate(new Expr.Constant(2));
        var sumThreeFive = new Expr.Addition(three, five);
        var multFirst = new Expr.Multiplication(sumThreeFive, negTwo);
        var multSecond = new Expr.Addition(multFirst, eight);
        var exp = new Expr.Exponent(multSecond, 3);
        var actual = new Expr.Addition(exp, new Expr.Constant(1));
        double expected = -511;
        assertEquals(expected, actual.evaluate());
    }
}
