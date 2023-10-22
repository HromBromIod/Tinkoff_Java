package edu.hw2.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task1 {
    private static final double FOUR = 4;
    private static final String EQUALS = " = ";

    private Task1() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        var zero = new Exponent(new Constant(0), -1);
        LOGGER.info(zero + EQUALS + zero.evaluate());

        var two = new Constant(2);
        var four = new Constant(FOUR);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        LOGGER.info(res + EQUALS + res.evaluate());
    }
}
