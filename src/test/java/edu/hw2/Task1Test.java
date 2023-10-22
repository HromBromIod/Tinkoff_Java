package edu.hw2;

import edu.hw2.task1.Expr;
import edu.hw2.task1.Constant;
import edu.hw2.task1.Negate;
import edu.hw2.task1.Exponent;
import edu.hw2.task1.Addition;
import edu.hw2.task1.Multiplication;
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
        assertEquals(expected, new Constant(actual).evaluate());
    }

    @ParameterizedTest(name = "Check Negate")
    @CsvSource({
        "1, -1",
        "-1, 1",
        "0, 0"
    })
    void checkNegateTest(double actual, double expected) {
        assertEquals(expected, new Negate(new Constant(actual)).evaluate());
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
        double actual = new Exponent(new Constant(valueLeft), degree).evaluate();
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
            new Addition(new Constant(valueLeft), new Constant(valueRight)).evaluate();
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
        double actual = new Multiplication(
            new Constant(valueLeft),
            new Constant(valueRight)
        ).evaluate();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("MultiCheck1")
    void multiCheckTest1() {
        Expr actual = new Addition(
            new Exponent(new Multiplication(new Addition(
                new Constant(2),
                new Constant(4)
            ), new Negate(new Constant(1))), 2),
            new Constant(1)
        );
        double expected = 37;
        assertEquals(expected, actual.evaluate());
    }

    @Test
    @DisplayName("MultiCheck2")
    void multiCheckTest2() {
        var three = new Constant(3);
        var five = new Constant(5);
        var eight = new Constant(8);
        var negTwo = new Negate(new Constant(2));
        var sumThreeFive = new Addition(three, five);
        var multFirst = new Multiplication(sumThreeFive, negTwo);
        var multSecond = new Addition(multFirst, eight);
        var exp = new Exponent(multSecond, 3);
        var actual = new Addition(exp, new Constant(1));
        double expected = -511;
        assertEquals(expected, actual.evaluate());
    }
}
